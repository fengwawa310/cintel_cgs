package com.service.proposal;

import java.util.List;

import com.entity.alarm.EtAlarm;
import com.entity.sys.SysNotice;

public interface ProposalService {

	List<SysNotice> findProposalList(SysNotice sysNotice);

	long countFindProposalList(SysNotice sysNotice);

	SysNotice selectByPrimaryKey(String id);

	long insertProposalData(SysNotice sysNotice);

}
