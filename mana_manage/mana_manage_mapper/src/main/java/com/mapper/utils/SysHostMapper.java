package com.mapper.utils;


import com.entity.sys.SysHost;
//import com.sun.tools.javac.util.List;

public interface SysHostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysHost record);

    int insertSelective(SysHost record);

    SysHost selectByPrimaryKey(Integer id);
    
    SysHost selectByPrimaryCode(String code);

    int updateByPrimaryKeySelective(SysHost record);

    int updateByPrimaryKey(SysHost record);
}