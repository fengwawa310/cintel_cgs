package com.entity.suspect;

import java.util.Date;

/**
 * 描述:et_friends表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-02-03
 */
public class EtFriends {
    /**
     * 主键
     */
    private String id;

    /**
     * 关联的重点人员编号
     */
    private String suspectNo;

    /**
     * 成员类型，参考字典“亲友关系”
     */
    private Integer roleType;

    /**
     * 描述具体的亲属关系
     */
    private String roleDes;

    /**
     * 亲属姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 与重点人员关系
     */
    private String relation;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 头像URL
     */
    private String headUrl;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 系统修改时间
     */
    private Date modifyTime;

    /**
     * 主键
     * @return ID 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 关联的重点人员编号
     * @return SUSPECT_NO 关联的重点人员编号
     */
    public String getSuspectNo() {
        return suspectNo;
    }

    /**
     * 关联的重点人员编号
     * @param suspectNo 关联的重点人员编号
     */
    public void setSuspectNo(String suspectNo) {
        this.suspectNo = suspectNo == null ? null : suspectNo.trim();
    }

    /**
     * 成员类型，参考字典“亲友关系”
     * @return ROLE_TYPE 成员类型，参考字典“亲友关系”
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * 成员类型，参考字典“亲友关系”
     * @param roleType 成员类型，参考字典“亲友关系”
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * 描述具体的亲属关系
     * @return ROLE_DES 描述具体的亲属关系
     */
    public String getRoleDes() {
        return roleDes;
    }

    /**
     * 描述具体的亲属关系
     * @param roleDes 描述具体的亲属关系
     */
    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes == null ? null : roleDes.trim();
    }

    /**
     * 亲属姓名
     * @return NAME 亲属姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 亲属姓名
     * @param name 亲属姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 身份证号
     * @return IDCARD 身份证号
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 身份证号
     * @param idcard 身份证号
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 与重点人员关系
     * @return RELATION 与重点人员关系
     */
    public String getRelation() {
        return relation;
    }

    /**
     * 与重点人员关系
     * @param relation 与重点人员关系
     */
    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    /**
     * 家庭住址
     * @return ADDRESS 家庭住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 家庭住址
     * @param address 家庭住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 头像URL
     * @return HEAD_URL 头像URL
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * 头像URL
     * @param headUrl 头像URL
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    /**
     * 备注
     * @return REMARKS 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 系统创建时间
     * @return CREAT_TIME 系统创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 系统创建时间
     * @param creatTime 系统创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 系统修改时间
     * @return MODIFY_TIME 系统修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 系统修改时间
     * @param modifyTime 系统修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}