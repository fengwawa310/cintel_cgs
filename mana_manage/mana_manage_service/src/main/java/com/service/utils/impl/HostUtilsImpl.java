package com.service.utils.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.sys.SysHost;
import com.mapper.utils.SysHostMapper;
import com.service.utils.HostUtils;

@Service
@Transactional
public class HostUtilsImpl implements HostUtils {

	@Resource
	private SysHostMapper sysHostMapper;
	/*
	 * (non-Javadoc)
	 * code 子系统标识 例如重点人员子系统code="suspect"
	 * @see com.service.utils.HostUtils#findAll(java.lang.String)
	 */
	@Override
	public SysHost findAll(String code) {
		SysHost sysHostMapperselectAll = sysHostMapper.selectByPrimaryCode(code);
		return sysHostMapperselectAll;
	}
	
}
