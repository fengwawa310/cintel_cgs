package com.service.infor;

import java.util.List;
import java.util.Map;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.infor.ApInforStaff;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.vo.infor.IntelligenceListRequetParam;
import com.vo.infor.IntelligenceListResponseVO;


public interface IntelligenceInterface {
	 
	PageHelpVO queryByIntelligenceListRequetParam(PageVO pageVO, IntelligenceListRequetParam intelligenceListRequetParam);
	
	int addIntelligence(IntelligenceListRequetParam intelligenceListRequetParam);

	void addXianYI(Map<String, String> map);

	int changeXiaFaType(Map<Object, Object> map);

	IntelligenceListResponseVO findListinforNo(String inforNo);

	int addflow(IntelligenceListRequetParam intelligenceListRequetParam);

	List<ApInforStaff> findXianYiRenList(String inforNo);

	void mergeSuspectIntegral(List<EtSuspectIntegral> etSuspectIntegrals);

	void mergeUnitIntegral(EtUnitIntegral etUnitIntegral);

	String getSysUserDicUnitName(String sysUserDicUnit);

}
