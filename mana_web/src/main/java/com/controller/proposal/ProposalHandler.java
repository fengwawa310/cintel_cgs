package com.controller.proposal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.sys.SysNotice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.IDGenerator;
import com.entity.sys.SysUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("proposalHandler")
public class ProposalHandler {

	private static String servicesCode = Const.SUB_SYSTEM_CODE_SYS;

	@RequestMapping("findProposalList")
	@ResponseBody
	public DatatablesResponse<SysNotice> findProposalList(HttpServletRequest request, HttpServletResponse response,
														  Integer start, Integer length) {
		SysUser user = (SysUser) request.getAttribute("user");

		String sendUnitCode = user.getLevel() == "1" ? user.getProvince()
				: (user.getLevel() == "2" ? user.getCity()
						: (user.getLevel() == "3" ? user.getArea() : user.getPoliceStation()));

		HashMap<String, Object> map = new HashMap<>();
		/*
		 * 这里仅以sendUnitCode作为接收参数，
		 * 在mapper中用于sendUnitCode和receiveUnitCode的where条件用
		 */
		map.put("sendUnitCode", sendUnitCode);
		map.put("start", start);
		map.put("length", length);

		String serviceUrl = "/proposalCon/findProposalList";
		String serviceCountUrl = "/proposalCon/countFindProposalList";
		int total = 0;
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, serviceUrl, map);
		String countResult = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, serviceCountUrl, map);

		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
		JSONObject countJson = JSONObject.fromObject(countResult);
		String countJsonStr = countJson.getString("data");
		total = Integer.parseInt(countJsonStr);
		@SuppressWarnings("unchecked")
		List<SysNotice> SysNoticeList = JSONArray.toList(jsonArray, new SysNotice(), new JsonConfig());
		DataSet<SysNotice> dataSet = new DataSet<SysNotice>(SysNoticeList, (long) total, (long) total);
		DatatablesResponse<SysNotice> resp = DatatablesResponse.build(dataSet);

		return resp;
	}

	/*
	 * 根据Id查询相关建议
	 */
	@RequestMapping("/selectByPrimaryKey")
	@ResponseBody
	public Map<String, String> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> mapResult = new HashMap<>();
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		;
		map.put("id", id);

		String serviceUrl = "/proposalCon/selectByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, serviceUrl, map);
		mapResult.put("data", result);
		return mapResult;
	}

	@RequestMapping("insertProposalData")
	@ResponseBody
	public String insertProposalData(HttpServletRequest request, HttpServletResponse response) {
		// TODO 待页面更新完善传输过来的代码

		SysUser user = (SysUser) request.getAttribute("user");
		String receiveUnitCode = request.getParameter("receiveUnitCode");
		String receiveUnitName = request.getParameter("receiveUnitName");
		String msgText = request.getParameter("msgText");

		HashMap<String, Object> sysNotice = new HashMap<>();
		sysNotice.put("id", IDGenerator.getorderNo());
		sysNotice.put("senderNo", user.getId());
		sysNotice.put("senderName", user.getUserName());
		sysNotice.put("msgClass", "3301");
		// 1省局、2市局、3分局、4派出所
		sysNotice.put("sendUnitCode",
				user.getLevel() == "1" ? user.getProvince()
						: (user.getLevel() == "2" ? user.getCity()
								: (user.getLevel() == "3" ? user.getArea() : user.getPoliceStation())));
		sysNotice.put("sendUnitName",
				user.getLevel() == "1" ? user.getProvinceName()
						: (user.getLevel() == "2" ? user.getCityName()
								: (user.getLevel() == "3" ? user.getAreaName() : user.getPoliceStationName())));
		sysNotice.put("receiveUnitCode", receiveUnitCode);
		sysNotice.put("receiveUnitName", receiveUnitName);
		sysNotice.put("msgText", msgText);
		//

		String serviceUrl = "/proposalCon/insertProposalData";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, serviceUrl, sysNotice);

		return result;
	}

}
