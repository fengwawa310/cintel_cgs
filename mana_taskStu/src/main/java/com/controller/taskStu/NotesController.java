package com.controller.taskStu;

import com.alibaba.fastjson.JSONArray;
import com.common.enums.EnumTypeVO;
import com.common.enums.EtJudgeEnumType;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.httpclient.HttpClientUtil;
import com.entity.DicUnit;
import com.entity.sys.SysUser;
import com.entity.task.EpAsjBl;
import com.entity.taskStu.ApJudgelog;
import com.entity.taskStu.EtJudgeflow;
import com.request.sys.PageVoReq;
import com.service.communal.DicUtilsService;
import com.service.sys.SysUserService;
import com.service.taskStu.ApJudgelogService;
import com.service.taskStu.EtJudgeflowService;
import com.service.taskStu.NotesService;
import com.vo.taskStu.EtJudgeflowVo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notes")
public class NotesController {
	

	@Resource
	private NotesService notesService;

	/**
	 *		/notes/selectById
	 * 按id查询公告详情
	 * @return
	 */
	@RequestMapping(value="/selectById",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EpAsjBl selectById(HttpSession httpSession, HttpServletRequest request, String id){
		EpAsjBl epAsjBl = new EpAsjBl();
		epAsjBl = notesService.selectByPrimaryKey(id);
//		EtJudgeflowVo etJudgeflowVo= EtJudgeflowVo.ValueOf(etJudgeflow);
//		EtJudgeflowVo etJudgeflowVo1 = EtjudgeFlowStateMethod(etJudgeflowVo);
		return epAsjBl;
	}

	/**
	 *      /notes/findNotesList
	 *
	 * 按用户查询公告列表
	 * @return
	 */
	@RequestMapping(value="/findNotesList",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageHelpVO findNotesList(HttpSession httpSession, HttpServletRequest request, PageVoReq pageVoReq,
									@RequestParam HashMap<String, Object> map
	){

		PageVO pageVO = new PageVO(Integer.parseInt(pageVoReq.getStart()),Integer.parseInt(pageVoReq.getLength()));
		PageHelpVO pageHelpVO =null;
		try {
			pageHelpVO=notesService.findNotesList(pageVO,map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}

}
