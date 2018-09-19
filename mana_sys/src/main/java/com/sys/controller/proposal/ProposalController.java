package com.sys.controller.proposal;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.sys.SysNotice;
import com.service.proposal.ProposalService;

@Controller
@RequestMapping("proposalCon")
public class ProposalController {

	@Resource
	private ProposalService proposalService;

	@RequestMapping(value = "/findProposalList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<SysNotice> findProposalList(SysNotice sysNotice) {
		return proposalService.findProposalList(sysNotice);
	}

	@RequestMapping(value = "/countFindProposalList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public long countFindProposalList(SysNotice sysNotice) {
		return proposalService.countFindProposalList(sysNotice);
	}
	
	@RequestMapping(value = "/selectByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public SysNotice selectByPrimaryKey(String id) {
		return proposalService.selectByPrimaryKey(id);
	}
	
	@RequestMapping(value = "/insertProposalData", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public long insertProposalData(SysNotice sysNotice) {
		return proposalService.insertProposalData(sysNotice);
	}

}
