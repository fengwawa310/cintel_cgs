package com.entity.sys;

/**
 * 描述:sys_host表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-09
 */
public class SysHost {
	
	
    /**
     * 主键
     */
    private Integer id;

    /**
     * Hoståç§°
     */
    private String code;

    /**
     * Host名称
     */
    private String name;

    /**
     * Host对应的URL地址
     */
    private String hostUrl;

    /**
     * 主键
     * @return ID 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Hoståç§°
     * @return CODE Hoståç§°
     */
    public String getCode() {
        return code;
    }

    /**
     * Hoståç§°
     * @param code Hoståç§°
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * Host名称
     * @return NAME Host名称
     */
    public String getName() {
        return name;
    }

    /**
     * Host名称
     * @param name Host名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * Host对应的URL地址
     * @return HOST_URL Host对应的URL地址
     */
    public String getHostUrl() {
        return hostUrl;
    }

    /**
     * Host对应的URL地址
     * @param hostUrl Host对应的URL地址
     */
    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl == null ? null : hostUrl.trim();
    }
}