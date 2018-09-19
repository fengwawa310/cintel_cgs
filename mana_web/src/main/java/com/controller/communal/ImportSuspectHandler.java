package com.controller.communal;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.*;
import com.common.utils.poiutil.PicData;
import com.common.utils.poiutil.PoiExcelUtil;
import com.common.utils.poiutil.ReadExcelCallBack;
import com.common.utils.poiutil.RowData;
import com.entity.suspect.EtSuspect;
import com.entity.sys.SysUser;
import com.service.fastdfs.FileCRUDService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Auri on 2018/3/14.
 * Desc:
 */
@Controller
@RequestMapping("importSuspectHandler")
public class ImportSuspectHandler {
    @Autowired
    private FileCRUDService fileCRUDService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject importTicket(HttpServletRequest request, MultipartFile file) throws IOException {
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        final String userId = user.getId();
        final String userName = user.getName();
        final String entryUnitCode = SysUser.getUnitCode(user);
        //文件名
        String fileName = file.getOriginalFilename();
        if (StringHelp.isEmpty(fileName)) {
            // 文件为空 反馈错误提示
            return buildErrorResult("请选择Excel文件！");
        }

        final int sheetNum = 0;
        final Map<Integer, PicData> picDatas = PoiExcelUtil.readExcelPic(file, sheetNum, new ReadExcelCallBack.ReadPicLisnr<PicData>() {
            @Override
            public PicData handlerPicData(int rowNum, byte[] picBytes) {
                String url = null;
                PicData picData = new PicData(rowNum);
                if (picBytes != null && picBytes.length > 0) {
                    Map<String, byte[]> files = new HashMap<>();
                    files.put("bytes", picBytes);
                    files.put("ext_Name", "PNG".getBytes());
                    files.put("fileName", "nofileName".getBytes());
                    url = fileCRUDService.upload(files).split("\\$\\$")[0];
                    picData.setFdfsUrl(url);
                    LogUtils.debug(url);
                }
                return picData;
            }
        });

        List<RowData<EtSuspect>> rowDatas = PoiExcelUtil.readExcelString(file, sheetNum, new ReadExcelCallBack.ReadValueLisnr<RowData<EtSuspect>>() {
            @Override
            public RowData<EtSuspect> handlerRowData(int rowNum, String[] values) {
                LogUtils.info(Arrays.toString(values));
                if (values == null || values.length == 0) {
                    return null;
                }
                // 第一列不是序号 直接忽略
                try {
                    int index = Integer.parseInt(String.valueOf(values[0]));
                    if (index > 0) {
                        // 获取头像路径
                        PicData picData = picDatas.get(rowNum);
                        String fdfsUrl = null;
                        if (picData != null) {
                            fdfsUrl = picData.getFdfsUrl();
                        }
                        // 识别姓名
                        String name = String.valueOf(values[1]);
                        // 识别别名
                        String byName = String.valueOf(values[2]);
                        // 识别身份证号码
                        String sss = String.valueOf(values[3]);
                        String regx = "(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?";
                        Pattern pp = Pattern.compile(regx);
                        Matcher mm = pp.matcher(sss);
                        if (!mm.find()) {
                            return null;
                        }
                        String idNum = mm.group(0);
                        // 组装备注
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 4; i < values.length; i++) {
                            String ss = String.valueOf(values[i]);
                            stringBuilder.append(ss + " ");
                        }
                        String remark = stringBuilder.toString();
                        EtSuspect suspect = new EtSuspect();
//                        suspect.setId(IDGenerator.getorderNo());
                        suspect.setSuspectId(IDGenerator.getorderNo());
                        suspect.setName(name);
                        suspect.setByname(byName);
                        suspect.setIdcardNum(idNum);
                        suspect.setDemo(remark);
                        suspect.setEntry(userId);
                        suspect.setEntryName(userName);
                        suspect.setEntryUnit(entryUnitCode);
                        suspect.setHeadPhotoUrl(fdfsUrl);
                        RowData<EtSuspect> rowData = new RowData<>(rowNum);
                        rowData.setData(suspect);
                        return rowData;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        if (rowDatas == null || rowDatas.isEmpty()) {
            // 文件为空 反馈错误提示
            return buildErrorResult("文件解析失败，请选择指定的Excel模板文件！");
        }

        String msg = dealRowData(rowDatas, userId);
        return buildSucsResult(msg);
    }

    private String dealRowData(List<RowData<EtSuspect>> rowDatas, String userId) {
        // 组装结果 string
        StringBuilder stringBuilder = new StringBuilder("操作完成<br/>");
        for (RowData<EtSuspect> rowData : rowDatas) {
            if (rowData == null || rowData.getData() == null) {
                continue;
            }
            int rowNum = rowData.getRowNum();
            // 保存入库
            EtSuspect suspect = rowData.getData();
            String etSuspectStr = GsonUtils.getGson().toJson(suspect);
            JSONObject etSuspectObj = JSONObject.fromObject(etSuspectStr);
            etSuspectObj.put("userId", userId);
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/save", etSuspectObj);
            // 解析结果
            try {
                JSONObject jsonObj = JSONObject.fromObject(jsonStr);
                if (jsonObj == null || !jsonObj.has("data")) {
                    stringBuilder.append("第" + rowNum + "行，身份证号" + suspect.getIdcardNum() + "数据导入失败。<br/>");
                    continue;
                }
                JSONObject dataObj = (JSONObject) jsonObj.get("data");
                Boolean flag = (Boolean) dataObj.get("flag");
                String msg = String.valueOf(dataObj.get("msg"));
                String id = String.valueOf(dataObj.get("id"));
                if (!flag) {
                    stringBuilder.append("第" + rowNum + "行，身份证号" + suspect.getIdcardNum() + "数据导入失败，" + msg + "<br/>");
                    continue;
                }
                // 同步至ES
                if (StringHelp.isNotEmpty(id)) {
                    Map<String, Object> paramMap = new HashMap<>();
                    paramMap.put("ID", id);
                    paramMap.put("NAME", suspect.getName());
                    paramMap.put("BYNAME", suspect.getByname());
                    paramMap.put("IDCARD_NUM", suspect.getIdcardNum());
                    paramMap.put("DEMO", suspect.getDemo());
                    ElasticSearchUtils.insert("et_suspect", "cgs", paramMap, id);
                }
            } catch (Exception ex) {
                stringBuilder.append("第" + rowNum + "行，身份证号" + suspect.getIdcardNum() + "数据导入失败。<br/>");
            }
        }
        return stringBuilder.toString();
    }


    // 构建错误提示
    private JSONObject buildErrorResult(String errMsgStr) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("flag", false);
        data.put("msg", String.valueOf(errMsgStr));
        result.put("data", data);
        return result;
    }

    // 构建错误提示
    private JSONObject buildSucsResult(String msgStr) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("flag", true);
        data.put("msg", String.valueOf(msgStr));
        result.put("data", data);
        return result;
    }


}
