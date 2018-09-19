package com.service.communal.impl;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.utils.IDGenerator;
import com.common.utils.UdpGetClientMacAddr;
import com.entity.sys.SysLog;
import com.entity.sys.SysUser;
import com.mapper.communal.SysLogMapper;
import com.service.communal.SysLogService;

import net.sf.json.JSONObject;
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
	
	@Resource
	private SysLogMapper sysLogMapper;

	@Override
	public int insertSysLog(String user, HttpServletRequest request,String remark) {
		try {
			
			/*{"address":"","area":"","areaName":"","city":"440300190400",
			"cityName":"深圳市CID二大队","code":"11122",
			"createTime":{"date":8,"day":1,"hours":10,"minutes":4,"month":0,"seconds":54,"time":1515377094000,"timezoneOffset":-480,"year":118},
			"createUser":"","delFlag":"0","email":"","id":"1","idcard":"","level":"2","loginFlag":"","logonIp":"",
			"logonTime":null,"name":"CID二大队","password":"202cb962ac59075b964b07152d234b70","policeStation":"",
			"policeStationName":"","position":"三级警监","province":"440000000000","provinceName":"","remark":"",
			"tel":"1388888888","updateTime":{"date":6,"day":2,"hours":10,"minutes":42,"month":1,"seconds":50,
				"time":1517884970000,"timezoneOffset":-480,"year":118},"updateUser":"1","userName":"tom"}*/
			JSONObject pageVOObj = JSONObject.fromObject(user);
			String level = pageVOObj.getString("level");
			String province = pageVOObj.getString("province");
			String city =pageVOObj.getString("city");
			String area =pageVOObj.getString("area");
			String policeStation =pageVOObj.getString("policeStation");
			String provinceName =pageVOObj.getString("provinceName");
			String policeStationName =pageVOObj.getString("policeStationName");
			String id =pageVOObj.getString("id");
			String userName =pageVOObj.getString("userName");
			String cityName =pageVOObj.getString("cityName");
			String areaName =pageVOObj.getString("areaName");
			SysLog sl = new SysLog();
			//String ipAddr = UdpGetClientMacAddr.getIp(request);
			//String macAddr = new UdpGetClientMacAddr(ipAddr).GetRemoteMacAddr();
			String unitCode = "1".equals(level) ? province
					: "2".equals(level) ? city
							: "3".equals(level) ? area : policeStation;
			String unitCodeName = "1".equals(level) ? provinceName
					: "2".equals(level) ? cityName
							: "3".equals(level) ? areaName : policeStationName;
			sl.setId(IDGenerator.getorderNo());
			sl.setOperatorCode(id);
			sl.setOperatorName(userName);
			sl.setOperatoUnitCode(unitCode);
			sl.setOperatoUnitName(unitCodeName);
			sl.setOperatoType(0);//暂无用途
			sl.setRemark(remark);
			sl.setOperatoTime(new Date());
			sl.setCreatTime(new Date());
			//sl.setOperatoIp(ipAddr);
			//sl.setOperatoMac(macAddr);
			// 1省局、2市局、3分局、4派出所
						/*String ipAddr = UdpGetClientMacAddr.getIp(request);
						String macAddr = new UdpGetClientMacAddr(ipAddr).GetRemoteMacAddr();
						String unitCode = "1".equals(user.getLevel()) ? user.getProvince()
								: "2".equals(user.getLevel()) ? user.getCity()
										: "3".equals(user.getLevel()) ? user.getArea() : user.getPoliceStation();
						String unitCodeName = "1".equals(user.getLevel()) ? user.getProvinceName()
								: "2".equals(user.getLevel()) ? user.getCityName()
										: "3".equals(user.getLevel()) ? user.getAreaName() : user.getPoliceStationName();
						sl.setId(IDGenerator.getorderNo());
						sl.setOperatorCode(user.getId());
						sl.setOperatorName(user.getUserName());
						sl.setOperatoUnitCode(unitCode);
						sl.setOperatoUnitName(unitCodeName);
						sl.setOperatoType(0);//暂无用途
						sl.setRemark(remark);
						sl.setOperatoTime(new Date());
						sl.setCreatTime(new Date());
						sl.setOperatoIp(ipAddr);
						sl.setOperatoMac(macAddr);*/
			return sysLogMapper.insertSelective(sl);
		} catch (Exception e) {
			return 100;
		}
	}

	@Override
	public int insertSysLog(SysUser user, HttpServletRequest request, String remark) {
		try {
			String level = user.getLevel();
			String province = user.getProvince();
			String city = user.getCity();
			String area = user.getArea();
			String policeStation = user.getPoliceStation();
			String provinceName = user.getProvinceName();
			String policeStationName = user.getPoliceStationName();
			String id = user.getId();
			String userName = user.getUserName();
			String cityName = user.getCityName();
			String areaName = user.getAreaName();
			SysLog sl = new SysLog();
			String ipAddr = UdpGetClientMacAddr.getIp(request);
			String macAddr = "";
			if(!"127.0.0.1".equals(ipAddr)){
				macAddr = new UdpGetClientMacAddr(ipAddr).GetRemoteMacAddr();
			}
			String unitCode = "1".equals(level) ? province
					: "2".equals(level) ? city
					: "3".equals(level) ? area : policeStation;
			String unitCodeName = "1".equals(level) ? provinceName
					: "2".equals(level) ? cityName
					: "3".equals(level) ? areaName : policeStationName;
			sl.setId(IDGenerator.getorderNo());
			sl.setOperatorCode(id);
			sl.setOperatorName(userName);
			sl.setOperatoUnitCode(unitCode);
			sl.setOperatoUnitName(unitCodeName);
			sl.setOperatoType(0);//暂无用途
			sl.setRemark(remark);
			sl.setOperatoTime(new Date());
			sl.setCreatTime(new Date());
			sl.setOperatoIp(ipAddr);
			sl.setOperatoMac(macAddr);
			return sysLogMapper.insertSelective(sl);
		} catch (Exception e) {
			return 100;
		}
	}

	@Override
	public List<SysLog> findSysLoglList(SysLog sysLog) {
		return sysLogMapper.findSysLoglList(sysLog);
	}

	@Override
	public List<SysLog> countFindSysLoglList(SysLog sysLog) {
		return sysLogMapper.countFindSysLoglList(sysLog);
	}

//	查询日志列表
	@Override
	public List<String> findLogList() {
		List<String> logs = new ArrayList<>();
		for (String log : sysLogMapper.findLogList()){
			if (!(StringUtils.isBlank(log) || "null".equals(log))){
				logs.add(log);
			}
		}
		return logs;
	}

}
