package com.util;

import com.common.consts.Const;
import com.common.enums.UnitLevelEnumType;
import com.entity.sys.SysUser;
import org.apache.commons.lang.StringUtils;

/**
 * 系统用户工具类
 *
 * @author admin
 * @create 2018-01-18 11:43
 **/
public class SysUserHelp {

    /*
    *  根据当前用户获取用户单位机构代码
    * */
    public static String getSysUserDicUnit(SysUser sysUser) {
        String dicUnit = "";
        if (sysUser != null && StringUtils.isNotBlank(sysUser.getLevel())) {
            if (sysUser.getLevel().equals(UnitLevelEnumType.PROVINCE.getValue())) {
                dicUnit = sysUser.getProvince();
            } else if (sysUser.getLevel().equals(UnitLevelEnumType.CITY.getValue())) {
                dicUnit = sysUser.getCity();
            } else if (sysUser.getLevel().equals(UnitLevelEnumType.COUNTY.getValue())) {
                dicUnit = sysUser.getArea();
            } else if (sysUser.getLevel().equals(UnitLevelEnumType.PCS.getValue())) {
                dicUnit = sysUser.getPoliceStation();
            }
        }
        return dicUnit;
    }
    
    /**
     * 获取用户分权分域标识
     * @param sysUser
     * @param sign 1 单位粒度 2 用户粒度
     * @return
     */
    public static String getUserDeceSigns(SysUser sysUser,String sign){
    	String result = "no user deceSigns";
    	if (sysUser != null && StringUtils.isNotBlank(sysUser.getLevel())) {
    		if("4".equals(sysUser.getLevel()))
    			result = "0,0,0,";
    		else{
    			if(Const.DECE_SIGNS_TYPE_UNIT.equals(sign)){
    				result = geneUnitId(sysUser.getProvince())+geneUnitId(sysUser.getCity())+geneUnitId(sysUser.getArea());
    			}else if(Const.DECE_SIGNS_TYPE_USER.equals(sign)){
    				result = geneUnitIdOfUser(sysUser.getProvince())+geneUnitIdOfUser(sysUser.getCity())+geneUnitIdOfUser(sysUser.getArea());
    			}
    		}
    		if(Const.DECE_SIGNS_TYPE_UNIT.equals(sign)){
    			result += StringUtils.isBlank(sysUser.getPoliceStation()) ? "":sysUser.getPoliceStation();
    		}else if(Const.DECE_SIGNS_TYPE_USER.equals(sign)){
    			result += StringUtils.isBlank(sysUser.getPoliceStation()) ? "0":sysUser.getPoliceStation();
    			result += "-"+sysUser.getId();
    		}
    	}
    	return result;
    }
    
    private static String geneUnitId(String unitId){
    	return StringUtils.isBlank(unitId) ? "":unitId+",";
    }
    
    private static String geneUnitIdOfUser(String unitId){
    	return (StringUtils.isBlank(unitId) ? "0":unitId)+",";
    }
}
