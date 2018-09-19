package com.service.suspect.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.suspect.RlSuspectGang;
import com.mapper.suspect.RlSuspectGangMapper;
import com.service.suspect.RlSuspectGangService;

@Service
@Transactional
public class RlSuspectGangServiceImpl implements RlSuspectGangService {

	@Resource
	private RlSuspectGangMapper rlSuspectGangMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		//将该ID的重点人员下级人员提档
		int  upgradeResult=rlSuspectGangMapper.upgrade(id);
		//if(upgradeResult>0){//该判断说明升级成功！
			//删除重点人员
			return rlSuspectGangMapper.deleteByPrimaryKey(id);
		//}
		//return 0;
	}

	@Override
	public int insert(RlSuspectGang record) {
		return rlSuspectGangMapper.insert(record);
	}

	@Override
	public int insertSelective(RlSuspectGang record) {
		return rlSuspectGangMapper.insertSelective(record);
	}

	@Override
	public List<Map<String,Object>> selectByPrimaryKey(String id) {
		return rlSuspectGangMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Map<String,Object>> selectByGangId(Map<String, Object> map) {
		return rlSuspectGangMapper.selectByGangId(map);
	}

	@Override
	public List<RlSuspectGang> selectRlsByGangId(String gangId) {
		return rlSuspectGangMapper.selectRlsByGangId(gangId);
	}

	@Override
	public int updateByPrimaryKeySelective(RlSuspectGang record) {
		return rlSuspectGangMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RlSuspectGang record) {
		return rlSuspectGangMapper.updateByPrimaryKey(record);
	}
	@Override
	public int deleteSubordinateByPrimaryKey(String id) {
		return rlSuspectGangMapper.deleteSubordinateByPrimaryKey(id);
	}

	/**
	 * @Author: sky
	 * @Description:根据重点人员id查询所有团伙id集合
	 * @Date: 下午3:26 2018/4/3
	 * @param: suspectId
	 */
	@Override
	public List<String> findGangsBySuspectId(String suspectId) {
		List<String> gangIdList = new ArrayList<>();
		List<RlSuspectGang> rlSuspectGangList = rlSuspectGangMapper.findGangsBySuspectId(suspectId);
		for (RlSuspectGang rlSuspectGang : rlSuspectGangList){
			gangIdList.add(rlSuspectGang.getGangId());
		}
		return gangIdList;
	}

}
