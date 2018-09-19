package  com.service.fastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @ClassName: FastDfsUtil
 * @Description: fastdfs文件操作工具类 1).初始化连接池； 2).实现文件的上传与下载;
 *
 */
@Transactional
public class FastDfsUtil {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(FastDfsUtil.class);

    /** 连接池 */
    private ConnectionPool connectionPool = null;
    /** 连接池默认最小连接数 */
    private long minPoolSize = 10;
    /** 连接池默认最大连接数 */
    private long maxPoolSize = 30;
    /** 当前创建的连接数 */
    private volatile long nowPoolSize = 0;
    /** 默认等待时间（单位：秒） */
    private long waitTimes = 200;

    /**
     * 初始化线程池
     *
     * @Description:
     *
     */
    public void init() {
        String logId = UUID.randomUUID().toString();
        LOGGER.info("[初始化线程池(Init)][" + logId + "][默认参数：minPoolSize="
                + minPoolSize + ",maxPoolSize=" + maxPoolSize + ",waitTimes="
                + waitTimes + "]");
        connectionPool = new ConnectionPool(minPoolSize, maxPoolSize, waitTimes);
    }

    /**
     *
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param groupName
     *            组名如group0
     * @param fileBytes
     *            文件字节数组
     * @param extName
     *            文件扩展名：如png
     * @param linkUrl
     *            访问地址：http://image.xxx.com
     * @return 图片上传成功后地址
     * @throws AppException
     *
     */
    public String upload(String groupName, byte[] fileBytes, String extName,
                         String linkUrl) throws AppException {
        String logId = UUID.randomUUID().toString();
        /** 封装文件信息参数 */
        NameValuePair[] metaList = new NameValuePair[] { new NameValuePair(
                "fileName", "") };
        TrackerServer trackerServer = null;
        try {

            /** 获取fastdfs服务器连接 */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer,
                    storageServer);

            /** 以文件字节的方式上传 */
            String[] results = client1.upload_file(groupName, fileBytes,
                    extName, metaList);

            /** 上传完毕及时释放连接 */
            connectionPool.checkin(trackerServer, logId);

            LOGGER.info("[上传文件（upload）-fastdfs服务器相应结果][" + logId
                    + "][result：results=" + results + "]");

            String result = null;

            /** results[0]:组名，results[1]:远程文件名 */
            if (results != null && results.length == 2) {
                return linkUrl + "/" + results[0] + "/" + results[1];
            } else {
                /** 文件系统上传返回结果错误 */
                throw ERRORS.UPLOAD_RESULT_ERROR.ERROR();
            }
        } catch (AppException e) {

            LOGGER.error("[上传文件（upload)][" + logId + "][异常：" + e + "]");
            throw e;

        } catch (SocketTimeoutException e) {
            LOGGER.error("[上传文件（upload)][" + logId + "][异常：" + e + "]");
            throw ERRORS.WAIT_IDLECONNECTION_TIMEOUT.ERROR();
        } catch (Exception e) {

            LOGGER.error("[上传文件（upload)][" + logId + "][异常：" + e + "]");
            connectionPool.drop(trackerServer, logId);
            throw ERRORS.SYS_ERROR.ERROR();

        }

    }

    /**
     *
     * @Description: 删除fastdfs服务器中文件
     * @param group_name
     *            组名
     * @param remote_filename
     *            远程文件名称
     * @throws AppException
     *
     */
    public void deleteFile(String group_name, String remote_filename)
            throws AppException {

        String logId = UUID.randomUUID().toString();
        LOGGER.info("[ 删除文件（deleteFile）][" + logId + "][parms：group_name="
                + group_name + ",remote_filename=" + remote_filename + "]");
        TrackerServer trackerServer = null;

        try {
            /** 获取可用的tracker,并创建存储server */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer,
                    storageServer);
            /** 删除文件,并释放 trackerServer */
            int result = client1.delete_file(group_name, remote_filename);

            /** 上传完毕及时释放连接 */
            connectionPool.checkin(trackerServer, logId);

            LOGGER.info("[ 删除文件（deleteFile）--调用fastdfs客户端返回结果][" + logId
                    + "][results：result=" + result + "]");

            /** 0:文件删除成功，2：文件不存在 ，其它：文件删除出错 */
            if (result == 2) {

                throw ERRORS.NOT_EXIST_FILE.ERROR();

            } else if (result != 0) {
                throw ERRORS.DELETE_RESULT_ERROR.ERROR();
            }

        } catch (AppException e) {

            LOGGER.error("[ 删除文件（deleteFile）][" + logId + "][异常：" + e + "]");
            throw e;

        } catch (SocketTimeoutException e) {
            LOGGER.error("[ 删除文件（deleteFile）][" + logId + "][异常：" + e + "]");
            throw ERRORS.WAIT_IDLECONNECTION_TIMEOUT.ERROR();
        } catch (Exception e) {
            LOGGER.error("[ 删除文件（deleteFile）][" + logId + "][异常：" + e + "]");
            connectionPool.drop(trackerServer, logId);
            throw ERRORS.SYS_ERROR.ERROR();

        }
    }
//	@Log(value="公共下载",params={@LogParam("下载地址")}, module = "1",type="4")
    public byte[] downLoadFile(String fileURL) {
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = null;
        byte[] buffer = null;
        fileURL=fileURL.split("\\$\\$")[0];
        if (fileURL != null && !"".equals(fileURL)) {
            String groupName = fileURL.split("/")[1];
            String url = fileURL.split(groupName + "/")[1];
            try {
                /** 获取可用的tracker,并创建存储server */
                trackerServer = connectionPool.checkout(logId);
                StorageServer storageServer = null;
                StorageClient1 client1 = new StorageClient1(trackerServer,
                        storageServer);
                // 从文件服务器获取文件字节
                buffer = client1.download_file(groupName, url);

                /** 上传完毕及时释放连接 */
                connectionPool.checkin(trackerServer, logId);

            } catch (IOException | MyException ex) {
                ex.printStackTrace();
            } catch (AppException e) {
                e.printStackTrace();
            }
        }
//		InputStream input = new ByteArrayInputStream(buffer);
        return buffer;
    }
    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public long getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(long minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public long getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(long maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public long getNowPoolSize() {
        return nowPoolSize;
    }

    public void setNowPoolSize(long nowPoolSize) {
        this.nowPoolSize = nowPoolSize;
    }

    public long getWaitTimes() {
        return waitTimes;
    }

    public void setWaitTimes(long waitTimes) {
        this.waitTimes = waitTimes;
    }
    /*
     * 上传文件
     */
    public String upload(Map<String,byte[]> files) {
        String ext_Name= null;
        String fileName= null;
        try {
            ext_Name=new String(files.get("ext_Name"),"utf-8");
            fileName = new String(files.get("fileName"),"utf-8");
        } catch (UnsupportedEncodingException e) {

        }
        String responseBody = "";
        byte[] bytes=null;
        bytes = files.get("bytes");
        String videoPath = uploadFile(bytes, ext_Name);
        responseBody = videoPath + "$$" + fileName + "@@";
        if (!"".equals(responseBody)) {
            responseBody = responseBody.substring(0, responseBody.length() - 2);
        }
        return responseBody;
    }
    public String uploadFile(byte[] byteFile, String ext_file) {
        StringBuffer sbPath = new StringBuffer();
        TrackerServer trackerServer = null;
        try {
            String logId = UUID.randomUUID().toString();
            /** 获取fastdfs服务器连接 */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer,
                    storageServer);
            /** 以文件字节的方式上传 */
            String[] results = client1.upload_file( byteFile,
                    ext_file, null);
//            client1.upload_file()
            /** 上传完毕及时释放连接 */
            connectionPool.checkin(trackerServer, logId);
            for (String string : results) {
                sbPath.append("/" + string);
                System.out.println(string);
            }
            // 全路径
            System.out.println(sbPath);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        } catch (AppException e) {
            e.printStackTrace();
        }
        return sbPath.toString();
    }

    /*
     * 文件删除方法 相关数据库中的数据请自行删除 如果需要删除多个文件
     */
    public String deleteFile(String fileURL) {
        String result = "false";
        TrackerServer trackerServer = null;
        String logId = UUID.randomUUID().toString();
        if (fileURL != null && !"".equals(fileURL)) {
            String[] split = fileURL.split("\\$\\$");
            String groupName = split[0].split("/")[1];
            String url = split[0].split(groupName + "/")[1];
            try {
                /** 获取可用的tracker,并创建存储server */
                trackerServer = connectionPool.checkout(logId);
                StorageServer storageServer = null;
                StorageClient1 client1 = new StorageClient1(trackerServer,
                        storageServer);
                /** 删除文件,并释放 trackerServer */
                int i = client1.delete_file(groupName, url);
                /** 上传完毕及时释放连接 */
                connectionPool.checkin(trackerServer, logId);
                LOGGER.info(i == 0 ? "删除成功" : "删除失败:" + i);
                result = (i == 0 ? "true" : "false");
                trackerServer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /*
    * 上传文件---测试暂时 不用。。。。
    */
    public String uploadFast(String fileName,String extName) {
        StringBuffer sbPath = new StringBuffer();
        TrackerServer trackerServer = null;
        try {
            String logId = UUID.randomUUID().toString();
            /** 获取fastdfs服务器连接 */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer,
                    storageServer);
            /** 以文件字节的方式上传 */

            String[] strings = client1.upload_file(fileName, extName, null);
            /** 上传完毕及时释放连接 */
            connectionPool.checkin(trackerServer, logId);
            for (String string : strings) {
                sbPath.append("/" + string);
                System.out.println(string);
            }
            // 全路径
            System.out.println(sbPath);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        } catch (AppException e) {
            e.printStackTrace();
        }
        return sbPath.toString();
    }

    private static void fileLocalSave(FastDfsUtil fastDfsUtil, String uploadName) throws InterruptedException, IOException {
        byte[] bytes1 = fastDfsUtil.downLoadFile(uploadName);
        OutputStream out = new FileOutputStream(new File("E:\\data\\"+1+".jpg"));
        out.write(bytes1);
        out.flush();
        out.close();
    }


    public static byte[] getByte(File file) throws Exception {
        byte[] bytes = null;
        if (file != null) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE) // 当文件的长度超过了int的最大值
            {
                System.out.println("this file is max ");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            // 如果得到的字节长度和file实际的长度不一致就可能出错了
            if (offset < bytes.length) {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
        return bytes;
    }

}
