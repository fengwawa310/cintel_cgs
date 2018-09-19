package com.mapper.infor;


import java.util.List;
import java.util.Map;

import com.entity.infor.ApInforStaff;
import com.entity.infor.EtInfor;
import com.entity.infor.EtInforWithBLOBs;
import com.vo.infor.Apstaff;
import com.vo.infor.IntelligenceListRequetParam;
import com.vo.infor.IntelligenceListResponseVO;

public interface EtInforMapper {
    int deleteByPrimaryKey(String id);

    int insert(EtInforWithBLOBs record);

    int insertSelective(EtInforWithBLOBs record);

    EtInforWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtInforWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EtInforWithBLOBs record);

    int updateByPrimaryKey(EtInfor record);

    /*查询下发列表*/
	List<IntelligenceListResponseVO> queryByIntelligenceListRequetParam(IntelligenceListRequetParam intelligenceListRequetParam);
	/*录入方法*/
	int addIntelligence(IntelligenceListRequetParam intelligenceListRequetParam);
	/*录入嫌疑人*/
	void addXianYI(Map<String, String> map);
	/*更改下发状态*/
	int changeXiaFaType(Map<Object, Object> map);

	IntelligenceListResponseVO findListinforNo(String inforNo);

	int addflow(IntelligenceListRequetParam intelligenceListRequetParam);

	List<ApInforStaff> findXianYiRenList(String inforNo);

	String getSysUserDicUnitName(String sysUserDicUnit);
}