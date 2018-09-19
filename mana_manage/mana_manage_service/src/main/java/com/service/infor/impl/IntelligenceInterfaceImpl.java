package com.service.infor.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.infor.ApInforStaff;
import com.entity.integral.EtSuspectIntegral;
import com.entity.integral.EtUnitIntegral;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.infor.EtInforMapper;
import com.mapper.utils.EtSuspectIntegralMapper;
import com.mapper.utils.EtUnitIntegralMapper;
import com.service.infor.IntelligenceInterface;
import com.vo.infor.IntelligenceListRequetParam;
import com.vo.infor.IntelligenceListResponseVO;


@Service
@Transactional
public class IntelligenceInterfaceImpl implements IntelligenceInterface {

	@Resource
	private EtInforMapper etInforMapper;
	
	@Autowired
	private EtSuspectIntegralMapper etSuspectIntegralMapper;
	
	
	@Autowired
	private  EtUnitIntegralMapper etUnitIntegralMapper;
	//所有列表 详情的查询方法
	 @Override
	    public PageHelpVO queryByIntelligenceListRequetParam(PageVO pageVO, IntelligenceListRequetParam intelligenceListRequetParam) {
	        Integer start = pageVO.getStart();
	        Integer length = pageVO.getLength();
	        PageHelper.startPage(start, length);
	        List<IntelligenceListResponseVO> list = etInforMapper.queryByIntelligenceListRequetParam(intelligenceListRequetParam);
	        PageInfo<IntelligenceListResponseVO> pageInfo = new PageInfo<>(list);
	        long total = pageInfo.getTotal();
	        PageHelpVO pageHelpVO = new PageHelpVO<IntelligenceListResponseVO>(total,list);
	        return pageHelpVO;
	    }
	//录入方法
	@Override
	public int addIntelligence(IntelligenceListRequetParam intelligenceListRequetParam) {
		int i = etInforMapper.addIntelligence(intelligenceListRequetParam);
		return i ;
	}



	@Override
	public int changeXiaFaType(Map<Object, Object> map ) {
		int i = etInforMapper.changeXiaFaType(map);
		return i;
	}
	
	//詳情
	@Override
	public IntelligenceListResponseVO findListinforNo(String inforNo) {
		return 	etInforMapper.findListinforNo(inforNo);
	}
	@Override
	public int addflow(IntelligenceListRequetParam intelligenceListRequetParam) {
		return etInforMapper.addflow(intelligenceListRequetParam);
	}
	@Override
	public void addXianYI(Map<String, String> map) {
		etInforMapper.addXianYI(map);
	}
	
	@Override
	public List<ApInforStaff> findXianYiRenList(String inforNo) {
		return etInforMapper.findXianYiRenList(inforNo);
	}
	
	//录入人员积分
	@Override
	public void mergeSuspectIntegral(List<EtSuspectIntegral> etSuspectIntegrals) {
		 etSuspectIntegralMapper.mergeSuspectIntegral(etSuspectIntegrals);
	}
	@Override
	public void mergeUnitIntegral(EtUnitIntegral etUnitIntegral) {
        etUnitIntegralMapper.mergeUnitIntegral(etUnitIntegral);
	}
	
	
	//获取单位名称
	@Override
	public String getSysUserDicUnitName(String sysUserDicUnit) {
		return etInforMapper.getSysUserDicUnitName(sysUserDicUnit);
	}
	 

}
