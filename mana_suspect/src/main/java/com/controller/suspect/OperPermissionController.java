package com.controller.suspect;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.suspect.OperPermission;
import com.service.suspect.OperPermissionService;

@Controller
@RequestMapping("OperPermissionCon")
public class OperPermissionController {

	@Resource
	private OperPermissionService OperPermissionService;

	@RequestMapping(value = "/deleteByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteByPrimaryKey(String id) {
		return OperPermissionService.deleteByPrimaryKey(id);
	}

	@RequestMapping(value = "/insert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insert(OperPermission record) {
		return OperPermissionService.insert(record);
	}

	@RequestMapping(value = "/insertSelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertSelective(OperPermission record) {
		return OperPermissionService.insertSelective(record);
	}

	@RequestMapping(value = "/selectByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public OperPermission selectByPrimaryKey(String gangLead) {
		return OperPermissionService.selectByPrimaryKey(gangLead);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateByPrimaryKeySelective(OperPermission record) {
		return OperPermissionService.updateByPrimaryKeySelective(record);
	}

	@RequestMapping(value = "/updateByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateByPrimaryKey(OperPermission record) {
		return OperPermissionService.updateByPrimaryKey(record);
	}

	@RequestMapping(value = "/selectOperPermissionBySuspectNoAndUserNo", produces = "application/json;charset=UTF-8")
	@ResponseBody
		/*根据嫌疑人编号和警员编号查询关联表信息*/
	public OperPermission selectOperPermissionBySuspectNoAndUserNo(OperPermission operPermission){
		String suspectNo = operPermission.getSuspectNo();
		String userNo = operPermission.getUserNo();
		OperPermission operPer = OperPermissionService.selectOperPermissionBySuspectNoAndUserNo(suspectNo,userNo);
		return operPer;
	}

}
