package com.controller.integral;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.SortList;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.entity.integral.StatisticsEntity;
import com.service.integral.IntegralService;

/**
 * 
 *
 * @author admin
 * @create 2017-12-29 16:29
 **/

@Controller
@RequestMapping("/integral")
public class IntegralController {

	@Resource
	private IntegralService integralService;

	/**
	 * 查询人员积分信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findSuspectIntegral", produces = "application/json; charset=utf-8")
	@ResponseBody
	public PageHelpVO<EtSuspectIntegral> findSuspectIntegral(HttpServletRequest request, PageVO page,
			EtSuspectIntegral si) {
		PageHelpVO<EtSuspectIntegral> pageHelpVO = null;
		try {
			pageHelpVO = integralService.findSuspectIntegral(page, si);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageHelpVO;
	}

	/**
	 * 查询单位积分信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findUnitIntegralStatistics", produces = "application/json; charset=utf-8")
	@ResponseBody
	public PageHelpVO<EtUnitIntegral> findUnitIntegralStatistics(HttpServletRequest request, PageVO page,
			EtUnitIntegral ui) {
		PageHelpVO<EtUnitIntegral> pageHelpVO = null;
		try {
			pageHelpVO = integralService.findUnitIntegralStatistics(page, ui);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}

	/**
	 * 查询单位积分信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "findUnitIntegral", produces = "application/json; charset=utf-8")
	@ResponseBody
	public PageHelpVO<EtUnitIntegral> findUnitIntegral(HttpServletRequest request, PageVO page, EtUnitIntegral ui) {
		PageHelpVO<EtUnitIntegral> pageHelpVO = null;
		try {
			pageHelpVO = integralService.findUnitIntegral(page, ui);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}

	private void calcTotal(List<StatisticsEntity> result) {
		if (result == null)
			return;
		StatisticsEntity totalEntity = new StatisticsEntity();
		totalEntity.setZoneName("合计");
		for (StatisticsEntity entity : result) {
			totalEntity
					.setCaseNum(Integer.valueOf(totalEntity.getCaseNum().intValue() + entity.getCaseNum().intValue()));
			totalEntity.setSuspectNum(
					Integer.valueOf(totalEntity.getSuspectNum().intValue() + entity.getSuspectNum().intValue()));
			totalEntity.setInforNum(
					Integer.valueOf(totalEntity.getInforNum().intValue() + entity.getInforNum().intValue()));
			totalEntity.setAlarmNum(
					Integer.valueOf(totalEntity.getAlarmNum().intValue() + entity.getAlarmNum().intValue()));
		}
		result.add(totalEntity);
	}

	/**
	 * 首页部分-统计
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/mainStatistics", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<StatisticsEntity> mainStatistics(HttpServletRequest request, HttpServletResponse response) {
		List<StatisticsEntity> result = integralService.dailyStatistics(null);
		// calcTotal(result);
		// 排序-案件数
		SortList<StatisticsEntity> sortList = new SortList<StatisticsEntity>();
		sortList.sort(result, "getCaseNum", "desc");
		return result;
	}

	/**
	 * 每日统计
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/dailyStatistics", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<StatisticsEntity> dailyStatistics(HttpServletRequest request, HttpServletResponse response) {
		String createTime = (String) request.getParameter("createTime");
		if (StringUtils.isBlank(createTime))
			return new ArrayList<StatisticsEntity>();

		List<StatisticsEntity> result = integralService.dailyStatistics(createTime);
		calcTotal(result);
		return result;
	}

	/**
	 * 每日通报
	 */
	@RequestMapping(value = "/dailyBriefing", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String dailyBriefing(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer result = new StringBuffer();
		String createTime = (String) request.getParameter("createTime");
		if (StringUtils.isBlank(createTime))
			return "";
		String[] rq = createTime.split("-");
		String rqStr = rq[1] + "月" + rq[2] + "日";
		//
		String qvStrs = "";
		Integer total = Integer.valueOf(0);
		List<StatisticsEntity> qyList = integralService.caseZoneStatistics(createTime);
		for (StatisticsEntity one : qyList) {
			qvStrs += one.getZoneName() + "、";
		}
		//
		List<StatisticsEntity> lxList = integralService.caseClassStatistics(createTime);
		String lxStrs = "", lxStrs_ = "";
		for (StatisticsEntity one : lxList) {
			lxStrs += one.getCaseClass() + "、";
			lxStrs_ += one.getCaseClass() + "<span class='ul-li-redNum'>" + one.getCaseNum() + "</span>起，";
			total += one.getCaseNum();
		}
		//
		result.append(
				"<ul class='ul-wrapper' style='display: block'><li><h4>每日综合情况：</h4></li><li><span>(一)、每日全市案件情况：</span></li>");
		result.append("<li><span><span>");
		result.append(rqStr);
		result.append("</span>，全市共抽取了相关案件<span class='ul-li-redNum'>");
		result.append(total);
		result.append("</span>起。其中");
		result.append(lxStrs_.length() > 0 ? lxStrs_.substring(0, lxStrs_.length() - 1) : lxStrs_);
		result.append("。</span></li><li>");
		result.append("<span>按案件类别分类(由多到少排序)：<span class='ul-li-blueText'>");
		result.append(lxStrs.length() > 0 ? lxStrs.substring(0, lxStrs.length() - 1) : lxStrs);
		result.append("。</span></span></li><li><span>按案件区域分类(由多到少排序)：<span class='ul-li-blueText'>");
		result.append(qvStrs.length() > 0 ? qvStrs.substring(0, qvStrs.length() - 1) : qvStrs);
		result.append("。</span></span></li><li><span><span>");
		result.append(rqStr);
		result.append("</span>，全市共录入案件<span class='ul-li-redNum'>");
		result.append(total);
		result.append("</span>起。</span></li></ul>");

		return result.toString();
	}

	@RequestMapping(value = "/updateUnitIntegral", produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONObject updateUnitIntegral(EtUnitIntegral etUnitIntegral) {
		JSONObject result = new JSONObject();
		try {
			this.integralService.updateUnitIntegral(etUnitIntegral);
			result.put("flag", true);
			result.put("msg", "编辑成功！");
		} catch (Exception e) {
			result.put("flag", false);
			result.put("msg", "编辑失败！原因：" + e);
			e.printStackTrace();
		}
		return result;
	}
}
