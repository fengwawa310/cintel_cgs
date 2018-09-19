package com.common.utils.poiutil;

import java.util.List;

/**
 * <P>
 * 
 * @ClassName:ReadExcelLisnr
 *                           </p>
 *                           <P>
 * @Description:读取Excel文件数据时触发的回调函数
 *                                  </p>
 *                                  <P>
 * @author:Administrator
 *                       </p>
 *                       <P>
 * @date:2018年3月15日 上午10:41:49
 *                  </p>
 */
public class ReadExcelCallBack
{
    // /**
    // * 处理一张图片数据
    // *
    // * @param rowNum
    // * 图片所在行数
    // * @param picBytes
    // * 图片字节数据
    // * @return
    // */
    // public T handlerPicData(int rowNum, byte[] picBytes);
    //
    // /**
    // * 处理一行数据
    // *
    // * @param rowData
    // */
    // public K handlerRowData(RowData rowData);

    public interface ReadPicLisnr<T>
    {
        /**
         * 处理一张图片数据
         * 
         * @param rowNum
         *            图片所在行数
         * @param picBytes
         *            图片字节数据
         * @return
         */
        public T handlerPicData(int rowNum, byte[] picBytes);
    }

    public interface ReadValueLisnr<K>
    {
        /**
         * 处理一行数据
         * 
         * @param rowNum
         *            图片所在行数
         * @param values
         */
        public K handlerRowData(int rowNum, String[] values);
    }

}
