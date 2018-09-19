package com.service.suspect.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.entity.DicCommon;
import com.mapper.communal.DicCommonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.suspect.EtGang;
import com.mapper.suspect.EtGangMapper;
import com.mapper.suspect.RlSuspectGangMapper;
import com.service.suspect.EtGangService;

@Service
@Transactional
public class EtGangServiceImpl implements EtGangService {

	@Resource
	private EtGangMapper etGangMapper;

	@Resource
	private RlSuspectGangMapper rlSuspectGangMapper;

	@Resource
	private DicCommonMapper dicCommonMapper;

	/*
	 * 删除该团伙时应该先删除其团伙下属的所有人员（rl_suspect_gang表）
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		int delRlResult=rlSuspectGangMapper.deleteByGangId(id);
		return etGangMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EtGang record) {
		return etGangMapper.insert(record);
	}

	@Override
	public int insertSelective(HashMap<String, Object> record) {
		return etGangMapper.insertSelective(record);
	}

	@Override
	public EtGang selectByPrimaryKey(String id) {
		return etGangMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EtGang record) {
		return etGangMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EtGang record) {
		return etGangMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertMap(HashMap<String, Object> record) {
		return etGangMapper.insertMap(record);
	}

	@Override
	public List<EtGang> selectByUserId(String userId) {
		List<EtGang> etGangs = etGangMapper.selectByUserId(userId);
		if(etGangs == null || etGangs.isEmpty())
		{
			return etGangs;
		}
		for(EtGang etGang : etGangs)
		{
			Integer gangType = etGang.getGangType();
			if(gangType != null)
			{
				DicCommon dic = dicCommonMapper.selectByDicCode(String.valueOf(gangType));
				if(dic != null)
				{
					etGang.setGangTypeStr(dic.getDicValue());
					continue;
				}
			}
			etGang.setGangTypeStr("");
		}
		return etGangs;
	}

	@Override
	public List<EtGang> selectGangBySuspectNo(Map<String, Object> map) {
		return etGangMapper.selectGangBySuspectNo(map);
	}

	@Override
	public int getGangNum() {
		return etGangMapper.getGangNum();
	}

}
