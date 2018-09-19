package com.service.proposal.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.proposal.ApMessage;
import com.entity.sys.SysNotice;
import com.mapper.sys.ApMessageMapper;
import com.mapper.sys.SysNoticeMapper;
import com.service.proposal.ProposalService;

@Service
@Transactional
public class ProposalServiceImpl implements ProposalService  {

	@Resource
	private SysNoticeMapper sysNoticeMapper;
	
	@Resource
	private ApMessageMapper apMessageMapper;
	
	@Override
	public List<SysNotice> findProposalList(SysNotice sysNotice) {
		return sysNoticeMapper.selectNoticListForProposal(sysNotice);
	}

	@Override
	public long countFindProposalList(SysNotice sysNotice) {
		return sysNoticeMapper.countFindProposalList(sysNotice);
	}

	@Override
	public SysNotice selectByPrimaryKey(String id) {
		return sysNoticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public long insertProposalData(SysNotice sysNotice) {
		/*long noticeId=sysNoticeMapper.insertSelective(sysNotice);  //insertProposalData(sysNotice);
		ApMessage apMessage= new ApMessage();
		apMessage.setMsgNo(sysNotice.getId());
		apMessage.setMsgText(sysNotice.getMsgText());
		long apMResult=apMessageMapper.insertSelective(apMessage);
		if(apMResult>0&&noticeId>0){
			return 1;
		}else{
			return 0;
		}*/
		return sysNoticeMapper.insertSelective(sysNotice); 
	}

}
