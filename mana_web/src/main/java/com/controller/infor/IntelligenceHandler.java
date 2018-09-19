package com.controller.infor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.services.api.APIClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.caseInfo.EtCase;
import com.entity.sys.SysUser;
import com.util.SysUserHelp;
import com.vo.infor.IntelligenceListRequetParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * 情报研判下发 intelligenceAssigned
 * 情报研判接收 intelligenceReceive
 * 情报研判上报 intelligenceReport
 * 情报研判处理 intelligenceDeal
 * ****
 * 页面调用 Controller 接收入口
 * @author admin
 * @create 2018-01-03 10:06
 **/

@RequestMapping("/IntelligenceHandler")
@Controller
public class IntelligenceHandler {
	private static final Logger logger = LoggerFactory.getLogger(IntelligenceHandler.class);
			// 情报研判查询列表
			@RequestMapping(value ="/list.action",produces = "application/json;charset=UTF-8")
			@ResponseBody
			public  DatatablesResponse list(HttpSession httpSession, HttpServletRequest request,
					@RequestParam(value = "search[value]") String search,
					@RequestParam(required = true, defaultValue = "1") Integer start,
					@RequestParam(required = false, defaultValue = "10") Integer length) {
				// 1 定义业务实体对象 GsonUtils。toBean(search,IntelligenceHandler.class))
				// IntelligenceListRequetParam 新建实体用于接收列表查询反馈结果
				try {
						String data = "";
						String descStr= URLDecoder.decode(search, "UTF-8").replace("\\","").replaceFirst("\"","");
						if(descStr != null && !"".equals(descStr)  ){
							 data = descStr.substring(0,descStr.lastIndexOf("\""));
						}
						
						
				String inforNo = request.getParameter("inforNo");	
				
		    	logger.info("情报查询流程：<WEB层 分发站>获取到的前台的页面参数为：" + data);
		    	SysUser sysUser = (SysUser) request.getAttribute("user");
		    	JSONObject jb = JSONObject.fromObject(sysUser);
		    	 
				IntelligenceListRequetParam intelligenceListRequetParam = GsonUtils.toBean(data, IntelligenceListRequetParam.class);
				if("".equals(intelligenceListRequetParam) || intelligenceListRequetParam == null){
					intelligenceListRequetParam = new IntelligenceListRequetParam();
				}
				intelligenceListRequetParam.setSysUser(JSONSerializer.toJSON(sysUser).toString());
				intelligenceListRequetParam.setLevel(jb.getString("level"));
				intelligenceListRequetParam.setArea( jb.getString("area"));
				intelligenceListRequetParam.setCity( jb.getString("city"));
				intelligenceListRequetParam.setPaichusuo(jb.getString("policeStation"));
				
				
				if(!"".equals(inforNo) && inforNo!=null ){
					intelligenceListRequetParam.setInforNo(inforNo);
				}
				
				 PageVO pageVO = new PageVO(start, length);
			        String pageVOStr = GsonUtils.getGson().toJson(pageVO);
			        JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
			        if(intelligenceListRequetParam!=null){
			            String intelligenceList = GsonUtils.getGson().toJson(intelligenceListRequetParam);
			            JSONObject intelligenceListObj = JSONObject.fromObject(intelligenceList);
			            pageVOObj.putAll(intelligenceListObj);
			        }
			        PageHelpVO pageHelpVO =null;
			        try {
			            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "IntelligenceController/list", pageVOObj);
			            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			            JSONObject respContent = (JSONObject) jsonObject.get("data");
			            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
			        DatatablesResponse<EtCase> resp = DatatablesResponse.build(dataSet);
			        return resp;
				} catch ( Exception e1) {
					e1.printStackTrace();
					return null;
				}
			}
			
		
	
	
	    //录入
	    @RequestMapping("/add.action")
	    @ResponseBody
	    public boolean add(HttpServletRequest request, HttpServletResponse response, 
	    				   @RequestBody String data) throws Exception{
	    	//权限留口 获取当前下发人和下发单位
			SysUser sysUser = (SysUser) request.getAttribute("user");
			  String sysUserDicUnit = SysUserHelp.getSysUserDicUnit(sysUser);
	    	//data用于接收接收前台ajax data中的所有参数 封装成json
	    	String descStr = URLDecoder.decode(data, "UTF-8").replace("\\","").replaceFirst("\"","");
	    	String string = descStr.substring(0,descStr.lastIndexOf("\""));
 	        HashMap<String, Object> hashMap = new HashMap<>();
	    	JSONObject js = JSONObject.fromObject(string);
			hashMap.put("inforTitle",js.getString("inforTitle"));
			//hashMap.put("inforClass",js.getString("inforClass"));
			hashMap.put("inforContent",js.getString("inforContent"));
			hashMap.put("advise",js.getString("advise"));
			hashMap.put("assistUnitName",js.getString("assistUnitName"));
			hashMap.put("assistUnitCode",js.getString("assistUnitCode"));
			hashMap.put("hostUnitCode",js.getString("hostUnitCode"));
			hashMap.put("hostUnitName",js.getString("hostUnitName"));
			hashMap.put("sysUser",JSONSerializer.toJSON(sysUser).toString());
			JSONArray xianyirenArray = js.getJSONArray("suspect");
		    hashMap.put("xianyirenArray", xianyirenArray);
		    hashMap.put("sysUserDicUnit", sysUserDicUnit);
			String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "IntelligenceController/add", hashMap);
			JSONObject jsonObject = JSONObject.fromObject(s);
			String date = jsonObject.getString("data");
			if("1".equals(date)){
				return true;
			}else{
			    return false;
			}
			
	    }

	    
	    /**
	     * 情報詳情
	     * @return
	     */
	    @RequestMapping(value ="/findListinforNo.action",produces = "application/json;charset=UTF-8")
	    @ResponseBody
	    public Map<String,Object> findListinforNo(HttpSession httpSession, HttpServletRequest request, String inforNo){
	        Map<String,Object> map = new HashMap<>();
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("inforNo",inforNo);
	        String jsonStr="";
	        try {
	            jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "IntelligenceController/findListinforNo", jsonObject);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        map.put("data",jsonStr);
	        return map;
	    }
	    
	    
	    /**
	     * 情報下发，签收，审核，上报，处理
	     * @return
	     */
	    @RequestMapping("/xiafa.action")
	    @ResponseBody
	    public Map<String,Object> xiafa(HttpSession httpSession, HttpServletRequest request, @RequestBody String inforNo){
	    	try {
	    		SysUser sysUser = (SysUser) request.getAttribute("user");
	    		  String sysUserDicUnit = SysUserHelp.getSysUserDicUnit(sysUser);
	    	Map<String,Object> map = new HashMap<>();
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("inforNo",inforNo);
	        jsonObject.put("sysUser",JSONSerializer.toJSON(sysUser).toString());
	        jsonObject.put("sysUserDicUnit", sysUserDicUnit);
	      
	        String jsonStr="";
	        try {
	            jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "IntelligenceController/xiafa", jsonObject);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        map.put("data",jsonStr);
	        return map;
	    	} catch (Exception e1) {
				e1.printStackTrace();
			return null;
			}
	    }
	    
}
