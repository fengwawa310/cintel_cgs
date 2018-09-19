package com.vo.suspect;

/**
 * Created by Auri on 2018/2/7.
 * Desc:
 */
public class SuspectTreePo {

    /**
     *  ets.SUSPECT_ID suspectId,
     ets.NAME name,
     ets.SUSPECT_TYPE type,
     ets.ENTRY_UNIT,
     oper.USER_NO,
     oper.PERMISSION_CODE
     */

    /**
     * 重点人员编号
     */
    private String suspectId;

    private String name;

    /**
     * 重点人员性质：字典"重点人员性质" 43重点人员性质： 4300 涉黑人员，4301 涉恶人员，4302 一般人员
     */
    private Integer suspectType;

    /**
     * 重点人员录入单位编码
     */
    private String entryUnit;

    private String userId;

    /**
     * 0建档人；1可查阅；2可查阅可编辑
     */
    private Integer permissionCode;

    public String getSuspectId() {
        return suspectId;
    }

    public void setSuspectId(String suspectId) {
        this.suspectId = suspectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSuspectType() {
        return suspectType;
    }

    public void setSuspectType(Integer suspectType) {
        this.suspectType = suspectType;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPermissionCode() {
        return permissionCode == null ? -1 : permissionCode;
    }

    public void setPermissionCode(Integer permissionCode) {
        this.permissionCode = permissionCode;
    }
}
