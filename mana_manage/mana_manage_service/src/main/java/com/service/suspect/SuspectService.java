package com.service.suspect;

import java.util.List;
import java.util.Map;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.integral.EtUnitIntegral;
import com.entity.suspect.EtSuspect;
import com.entity.suspect.EtVehicle;
import com.entity.ticket.EtTicket;
import com.param.suspect.EtSuspectDTO;


/**
 * 人员service表
 *
 * @author OneDove
 *
 */
public interface SuspectService {

	/*查询改人员是否已录入*/
	EtSuspect selectIDCardNum(EtSuspect etSuspect);
	/**
	 * 添加重点人员
	 * @param entity 重点人员基本信息
	 * @param etUnitIntegral
	 * @param vehicles 重点人员车辆信息
	 * @return suspect数据库主键ID
	 */
	String save(EtSuspect entity, EtUnitIntegral etUnitIntegral, String vehicles,String userId);

	int delete(String id);

	int edit(EtSuspect entity);

	EtSuspect find(String mobilephone);

	PageHelpVO findticket(PageVO pageVO,EtTicket etSuspect);

	PageHelpVO findAll(PageVO pageVO, EtSuspect etSuspect);

	int editStatus(EtSuspect qe);

	int searchIdCard(EtSuspect etSuspect);

	int changeCtrlState(String suspectNo, Integer ctrlStatCode);

	List<EtSuspect> listAllForOperator(Map<String, Object> map);
	/*根据重点人员编号查找对应的重点人员的警员信息*/
	PageHelpVO suspectAndpoliceList(PageVO pageVO, EtSuspectDTO etSuspect);
	/*根据重点人员列表查询*/
	PageHelpVO findSuspectList(PageVO pageVO, EtSuspectDTO etSuspect);
	/*
	 * 根据重点人员编号查询该重点人员所有授予的前线列表信息
	 */
	PageHelpVO findUserListForSuspect(PageVO pageVO, EtSuspect etSuspect);

	PageHelpVO<EtVehicle> findVehicleForSuspect(PageVO pageVO, EtVehicle etVehicle);

	/*查询重点人员信息*/
	Map<String,Object> findPersonById(Map<String, Object> map);

	//根据姓名和身份证判断查询重点人员
	EtSuspect findSuspectByBaseinfo(EtSuspect etSuspect);
	//根据团伙id获取团伙人员
	List<EtSuspect> selectByGangId(String gangId);
	//查询重点人员查看权限
    Map<String,Object> suspectRole(Map<String, Object> map);

//    获取重点人员总数
    int getSuspectNum();
}
