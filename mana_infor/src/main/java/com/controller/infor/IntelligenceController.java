package com.controller.infor;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.TimeUtil;
import com.entity.infor.ApInforStaff;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.entity.message.MessageList;
import com.entity.suspect.EtWarning;
import com.service.communal.MessageListService;
import com.service.communal.SysLogService;
import com.service.infor.IntelligenceInterface;
import com.service.utils.EWSurveilService;
import com.service.utils.IntegralCalculationService;
import com.vo.infor.IntelligenceListRequetParam;
import com.vo.infor.IntelligenceListResponseVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/IntelligenceController")
public class IntelligenceController {
	// 日志
	private static final Logger logger = LoggerFactory.getLogger(IntelligenceController.class);

	@Autowired
	private IntelligenceInterface intelligenceInterface;
	
	
	@Autowired
	private IntegralCalculationService integralCalculationService;
	
	@Autowired
	private MessageListService messageListService;
	
	@Autowired
	private EWSurveilService ewsurveilservice;
	
	@Autowired
	private SysLogService syslogservice;

	/*
	 * 接收由主项目 分发过来的内容 IntelligenceListRequetParam 请求过来的封装后的实体参数
	 * IntelligenceListResponseVO 响应由后台数据库查询列表数据 查询下发列表的方法 入口
	 * IntelligenceControllerInfo/list 详情 列表 等一系列查询均走此 list方法
	 */
	@RequestMapping(value ="/list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	private  PageHelpVO list(HttpSession httpSession, 
										HttpServletRequest request,PageVO pageVO,
										IntelligenceListRequetParam intelligenceListRequetParam) {
	    PageHelpVO pageHelpVO =null;
        try {
            pageHelpVO=intelligenceInterface.queryByIntelligenceListRequetParam(pageVO,intelligenceListRequetParam);
            String remark = "情报--查询功能";
            String  sysUserString = intelligenceListRequetParam.getSysUser();
            try {
            	 int insertSysLog = syslogservice.insertSysLog(sysUserString, request, remark);
                 if(insertSysLog == 100){
                 	logger.info("日志存入失败");
                 }
			} catch (Exception e) {
			}
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHelpVO;
	}

	// 在录入的时候 需要一个上传文件返回的路径
	// 录入
	@RequestMapping("/add")
	@ResponseBody
	private String add(HttpSession httpSession, 
			HttpServletRequest request,
			IntelligenceListRequetParam intelligenceListRequetParam
			) {
		try {
			intelligenceListRequetParam.setId(IDGenerator.getorderNo());
			intelligenceListRequetParam.setInforNo(IDGenerator.getorderNo());
			  String user = intelligenceListRequetParam.getSysUser();
			  JSONObject jb = JSONObject.fromObject(user);
			  String level = jb.getString("level");
			  
			  if("1".equals(level)){
				  intelligenceListRequetParam.setLaunchUnitName("最高局");//登录人归属派出所
				  intelligenceListRequetParam.setLaunchPname("最高领导人");//登录人
			  }
			  if("2".equals(level)){
				  intelligenceListRequetParam.setLaunchUnitCode(jb.getString("city"));
				intelligenceListRequetParam.setLaunchUnitName(jb.getString("cityName"));//登录人归属派出所
				intelligenceListRequetParam.setLaunchPname(jb.getString("name"));//登录人 
			  }
			  if("3".equals(level)){
				  intelligenceListRequetParam.setLaunchUnitCode(jb.getString("area"));
				  intelligenceListRequetParam.setLaunchUnitName(jb.getString("areaName"));//登录人归属派出所
				  intelligenceListRequetParam.setLaunchPname(jb.getString("name"));//登录人 
			  }
			
			//案件预警
			List<EtWarning> etWList = new ArrayList<>();
			//代码流程-------------------- 录入Start -------------------------
			int i = intelligenceInterface.addIntelligence(intelligenceListRequetParam);
			if(i == 1){ //入库成功
				logger.info(" 情报管理流程log====  录入成功");
				//情报管理两张表  当主表录入成功后 录入 流程操作表
				 int kk = intelligenceInterface.addflow(intelligenceListRequetParam);
				 if(kk == 1){
					//代码流程-------------------- 录入嫌疑人Start -------------------------
						String xianyirenArray = intelligenceListRequetParam.getXianyirenArray();
						JSONArray js = JSONArray.fromObject(xianyirenArray);
					    Map<String,String> map = new HashMap<String,String>();
					    int count = 0;
					    EtWarning etW = new EtWarning();
						for (int k = 0; k < js.size(); k++) {
							JSONObject object = JSONObject.fromObject(js.get(k));
							map.put("id", IDGenerator.getorderNo());
							map.put("inforNo", intelligenceListRequetParam.getInforNo());
							map.put("xianyirenname", object.getString("name"));
							map.put("xianyirenshenfenzheng", object.getString("idCard"));
							etW.setWarningClass(1702);//来源
							etW.setbCtrlIdcardNum(map.get("xianyirenshenfenzheng"));//身份证
							etW.setbCtrlName(map.get("xianyirenname"));//身份证号
							etW.setWarningDetal("情报编号为：" + intelligenceListRequetParam.getInforNo() + "情报录入嫌疑人身份证号" + map.get("xianyirenshenfenzheng") );
							etW.setRelationNo(intelligenceListRequetParam.getInforNo());
							etWList.add(etW);
							intelligenceInterface.addXianYI(map);
							count ++;
							logger.info(" 情报管理流程log====  一共录入"+ count + "次嫌疑人");
							//代码流程-------------------- 录入嫌疑人Start -------------------------
							//代码流程-------------------- 录入End -------------------------
						}
				 }
				 else {
					logger.info(" 情报管理流程log====  录入失败");
					return "2";
				 }
				
				try {
					//代码流程---------------------调用案件不控----------------------
					anJianBukong(etWList,(String) jb.get("id"));
					//代码流程-------------------- 调用积分 -------------------------
					findjifen(intelligenceListRequetParam.getInforNo(),null);
				} catch (Exception e) {
					 e.printStackTrace();
				}  
				return "1";
			}else{
				logger.info(" 情报管理流程log====  录入失败");
				return "2";
			}
		} catch (Exception e) {
			logger.error("情报管理流程log====  录入失败");
			return "0";
		}
	}

    /**
     * 将情报生成过程中产生的预警信息入库，同时对每一条预警匹配创建一条系统通知消息信息并入库。
     * @param etWList
     */
	private void anJianBukong(List<EtWarning> etWList,String userId) {
        List<String> warningNos = ewsurveilservice.insertIntoWarning(etWList);
        logger.info("案件布控预警接口返回的，已成功创建的预警信息编号：" + warningNos.toString());
        if (warningNos == null || warningNos.isEmpty()) {
            return;
        }
        int num = 0;
        for(int i = 0;i<etWList.size();i++)
        {
            String createTimeStr = TimeUtil.formatDateToStr(new Date(),null);
            EtWarning etW = etWList.get(i);
            MessageList messageList = new MessageList();
            messageList.setId(IDGenerator.getorderNo());
            messageList.setTitle("有新预警产生，编号<" + etW.getWarningId() + ">，请进行处理");
            messageList.setReceiveUnitCode(etW.getManaUnitCode());
            messageList.setReceiveUnitName(etW.getManaPCode());
            messageList.setReceiverCode("");
            messageList.setRelationNo(etW.getWarningId());
            messageList.setRelationClass(Const.XIAOXI_QINGBAO_YUJING);
            messageList.setIsSend(0);
            messageList.setIsRead(0);
            messageList.setCreatTime(createTimeStr);
            num += messageListService.add(messageList);
        }
        logger.info("系统通知消息接口返回的，已成功创建的消息数目：" + num);
	}

	// 下发/签收/审核 公共方法 根据登录的人员进行判定谁能更改什么事情
	@RequestMapping("/xiafa")
	@ResponseBody
	private String xiafa(HttpSession httpSession, 
			HttpServletRequest request,
			IntelligenceListRequetParam intelligenceListRequetParam) {
		try {
		Map<Object, Object> map = new HashMap<Object, Object>();
	
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		//String time = sdf.format(date);
		Timestamp timestamp = new Timestamp((new Date()).getTime())  ;
		map.put("time", timestamp);
			// 获取情报编号
			String descStr = URLDecoder.decode(intelligenceListRequetParam.getInforNo(), "UTF-8").replace("\\","");
			JSONObject js = JSONObject.fromObject(descStr);
			String inforNo = js.getString("inforNo");
			intelligenceListRequetParam.setInforNo(inforNo);
			String liuchengType = js.getString("liuchengType");
			MessageList messageList = new MessageList();
			String user = intelligenceListRequetParam.getSysUser();
			JSONObject jb = JSONObject.fromObject(user);
			intelligenceListRequetParam.setReceiveUnitName(jb.getString("areaName"));
			intelligenceListRequetParam.setReceivePersonName(jb.getString("name"));
			
			//获取该单位编码所对应的单位名称
			String sysUserDicUnit = intelligenceListRequetParam.getSysUserDicUnit();
			String sysUserDicUnitName = intelligenceInterface.getSysUserDicUnitName(sysUserDicUnit);
			//下发
			if("1".equals(liuchengType)){
				map.put("isHandout", 1);
				map.put("xiafadanweibianhao", sysUserDicUnit);
				map.put("xiafadanweimingcheng", sysUserDicUnitName);
				map.put("xiafarenbianhao", jb.getString("province"));
				map.put("xiafarenmingcheng", jb.getString("name"));
			}
			//签收
			if("2".equals(liuchengType)){
				map.put("isReceive", 1);
				map.put("receiveUnit", sysUserDicUnit);
				map.put("receiveUnitName", sysUserDicUnitName);
				map.put("receivePerson", jb.getString("province"));
				map.put("receivePersonName", jb.getString("name"));
			}
			//签收审核
			if("3".equals(liuchengType)){
				String shenhetype = js.getString("shenhetype");
				if("1".equals(shenhetype)){
				map.put("isAuthorize", 1);//接收审核
				}
				if("2".equals(shenhetype)){
				map.put("isAuthorize", 2);//接收审核
				}
				map.put("authorizeUnit", sysUserDicUnit);
				map.put("authorizeUnitName", sysUserDicUnitName);
				map.put("authorizePerson", jb.getString("province"));
				map.put("authorizePersonName", jb.getString("name"));
				//截取审核意见
		    	String shenheyijian = inforNo.substring(inforNo.indexOf("=")+1);
		    		   inforNo = inforNo.substring(0,inforNo.indexOf("="));
		    		   intelligenceListRequetParam.setInforNo(inforNo);
		    	map.put("authorizeRemark", shenheyijian);
			}
			
			//上报
			if("4".equals(liuchengType)){
			String process = js.getString("process");
			String result = js.getString("result");
			map.put("isReport", 1);
			map.put("reportUnitName", sysUserDicUnitName);	
			map.put("reportUnit",  sysUserDicUnit);	
			map.put("reportPerson", jb.getString("province"));	
			map.put("reportPersonName", jb.getString("name"));	
			map.put("process", process);	
			map.put("result", result);	
			}
			
			//上报审核
			if("5".equals(liuchengType)){
			String shangbaoshenhetype = js.getString("shangbaoshenhetype");
	    	String shangbaoshenheyijian = inforNo.substring(inforNo.indexOf("=")+1);
	    	inforNo = inforNo.substring(0,inforNo.indexOf("="));
	    	intelligenceListRequetParam.setInforNo(inforNo);
	    	map.put("shangbaoshenheyijian", shangbaoshenheyijian);
	    	if("1".equals(shangbaoshenhetype)){
				map.put("isVerify", 1);//上报审核成功
			}
			if("2".equals(shangbaoshenhetype)){
				map.put("isVerify", 2);//上报审核失败
			}
			map.put("verifyUnitName", sysUserDicUnitName);
			map.put("verifyUnit", sysUserDicUnit);
			map.put("verifyPerson", jb.getString("province"));
			map.put("verifyPersonName",jb.getString("name"));
			map.put("verifyRemark",shangbaoshenheyijian);
			
			}
			
		map.put("inforNo", inforNo);	
		// 更改下发/签收/签收审核/上报/上报审核的标识
		int i = intelligenceInterface.changeXiaFaType(map);
		try {
			if(i == 1){
				if("1".equals(liuchengType)){
					findjifen("", intelligenceListRequetParam);
					messageListByintelligenceListRequetParam(messageList,intelligenceListRequetParam,Const.XIAOXI_QINGBAO_JIESHOU,(String) jb.get("id"));
				}
				if("2".equals(liuchengType)){
					messageListByintelligenceListRequetParam(messageList,intelligenceListRequetParam,Const.XIAOXI_QINGBAO_JIESHOUSHENHE,(String) jb.get("id"));
				}
				if("3".equals(liuchengType)){
					messageListByintelligenceListRequetParam(messageList,intelligenceListRequetParam,Const.XIAOXI_QINGBAO_SHANGBAO,(String) jb.get("id"));
				}
				if("4".equals(liuchengType)){
					messageListByintelligenceListRequetParam(messageList,intelligenceListRequetParam,Const.XIAOXI_QINGBAO_CHULI,(String) jb.get("id"));
				}
				/*if("5".equals(liuchengType)){
					messageListByintelligenceListRequetParam(messageList,intelligenceListRequetParam,Const.XIAOXI_QINGBAO_CHULI);
				}*/
				messageListService.add(messageList); 
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		if (i == 1) {
			logger.info("情报管理下发流程log====  入库成功");
			return "1";
		} else {
			logger.info("情报管理下发流程log====  入库失败");
			return "0";
		}
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
		
	}
	
	private MessageList messageListByintelligenceListRequetParam( MessageList messageList,IntelligenceListRequetParam intelligenceListRequetParam,String bianhao,String id) {
		IntelligenceListResponseVO intelligenceListResponseVO=intelligenceInterface.findListinforNo(intelligenceListRequetParam.getInforNo());
		messageList.setId(IDGenerator.getorderNo());
		messageList.setTitle("请针对情报编号<"+intelligenceListRequetParam.getInforNo()+">进行处理");
		messageList.setReceiverCode(id);
		messageList.setReceiveUnitCode(intelligenceListResponseVO.getHostUnitCode()+ intelligenceListResponseVO.getAssistUnitCode());
		messageList.setReceiveUnitName(intelligenceListRequetParam.getReceiveUnitName());
		messageList.setRelationNo(intelligenceListRequetParam.getInforNo());
		messageList.setRelationClass(bianhao);
		messageList.setIsSend(0);
		messageList.setIsRead(0);
		return messageList;
	}

	//调用积分方法
	private void findjifen(String inforNo,IntelligenceListRequetParam intelligenceListRequetParam) {
		 //根据情报编号 查询所有 当前录入的人员实体
		 List<ApInforStaff> tt = intelligenceInterface.findXianYiRenList(inforNo);
		 
		 //代码流程-------------------- 调用人员积分 -------------------------
		 if(!"".equals(inforNo) && inforNo != null){
			 List<EtSuspectIntegral> etSuspectIntegrals = integralCalculationService.integralCalcOfSuspectList(tt);
	         if(etSuspectIntegrals!=null && etSuspectIntegrals.size() > 0 ){
	        	 intelligenceInterface.mergeSuspectIntegral(etSuspectIntegrals);
	         }
		 }else{
			 //代码流程-------------------- 调用单位积分 -------------------------
	         //单位积分
			 //模拟数据 测试单位积分
			 intelligenceListRequetParam.setHandoutUnit("440303001");
			 intelligenceListRequetParam.setHandoutUnitName("翠竹派出所");
	         EtUnitIntegral etUnitIntegral = integralCalculationService.integralCalcOfUnit(Const.INTEGRAL_UNIT_QB, intelligenceListRequetParam.getHandoutUnit(), intelligenceListRequetParam.getHandoutUnitName());
	         if(etUnitIntegral!=null){
	        	 intelligenceInterface.mergeUnitIntegral(etUnitIntegral);
	         } 
		 }
	}
	
	   /**
     * 情報詳情
     * @return
     */
    @RequestMapping(value ="findListinforNo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public IntelligenceListResponseVO findListinforNo(HttpSession httpSession, HttpServletRequest request, String inforNo){
    	IntelligenceListResponseVO intelligenceListResponseVO =null;
        try {
        	intelligenceListResponseVO=intelligenceInterface.findListinforNo(inforNo);
        	List<ApInforStaff> list = intelligenceInterface.findXianYiRenList(inforNo);
        	intelligenceListResponseVO.setXianyirenList(list);
        	testReflect(intelligenceListResponseVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intelligenceListResponseVO;
    }
    
    public static void testReflect(Object model) throws Exception{
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + ":" + field.get(model) );
        }
    }
    
    public static void main(String[] args) {
    	String inforNo = "1111=审核意见";
    	String string = inforNo.substring(inforNo.indexOf("=")+1);
    	String inforNoV = inforNo.substring(0,inforNo.indexOf("="));
    	System.out.println(string);
    	System.out.println(inforNoV);
    	
	}
}
