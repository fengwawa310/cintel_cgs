package com.entity.infor;

/**
 * 描述:b_infor_flow表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-01-03
 */
public class BInforFlow {
    /**
     * 主键
     */
    private String id;

    /**
     * 情报编号
     */
    private String inforNo;

    /**
     * 流转步骤 字典“情报流转步骤”
     */
    private Integer flowStep;

    /**
     * 流转类型 字典“流转类型”
     */
    private Integer flowType;

    /**
     * 情报派发单位是否下发：0未下发；1已下发。默认0；对应“报研判下发”步骤
     */
    private Integer isHandout;

    /**
     * 情报下发单位编码
     */
    private String handoutUnit;

    /**
     * 情报下发人员编码
     */
    private String handoutPerson;

    /**
     * 情报查证单位是否签收：0未签收；1已签收。默认0；对应“情报研判签收”步骤
     */
    private Integer isSign;

    /**
     * 情报签收单位编码
     */
    private String signUnit;

    /**
     * 情报签收人员编码
     */
    private String signPerson;

    /**
     * 情报查证单位是否审核通过（接受）：0未接受；1接受。默认0；对应“情报签收审核”步骤
     */
    private Integer isAccept;

    /**
     * 情报接收审核单位编码
     */
    private String acceptUnit;

    /**
     * 情报接收审核人员编码
     */
    private String acceptPerson;

    /**
     * 情报查证单位查证完毕后是否上报：0未上报；1已上报。默认0；对应“情报研判上报”步骤
     */
    private Integer isReport;

    /**
     * 研判上报单位编码
     */
    private String reportUnit;

    /**
     * 研判上报人员编码
     */
    private String reportPerson;

    /**
     * 情报派发单位审核上报是否通过：0不通过；1通过。默认0；对应 “情报研判审核”步骤
     */
    private Integer isVerify;

    /**
     * 研判审核单位编码
     */
    private String verifyUnit;

    /**
     * 研判审核人员编码
     */
    private String verifyPerson;

    /**
     * 情报下发单位是否采用：0未采用；1已采用；默认0
     */
    private Integer isUse;

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
     * 情报编号
     * @return INFOR_NO 情报编号
     */
    public String getInforNo() {
        return inforNo;
    }

    /**
     * 情报编号
     * @param inforNo 情报编号
     */
    public void setInforNo(String inforNo) {
        this.inforNo = inforNo == null ? null : inforNo.trim();
    }

    /**
     * 流转步骤 字典“情报流转步骤”
     * @return FLOW_STEP 流转步骤 字典“情报流转步骤”
     */
    public Integer getFlowStep() {
        return flowStep;
    }

    /**
     * 流转步骤 字典“情报流转步骤”
     * @param flowStep 流转步骤 字典“情报流转步骤”
     */
    public void setFlowStep(Integer flowStep) {
        this.flowStep = flowStep;
    }

    /**
     * 流转类型 字典“流转类型”
     * @return FLOW_TYPE 流转类型 字典“流转类型”
     */
    public Integer getFlowType() {
        return flowType;
    }

    /**
     * 流转类型 字典“流转类型”
     * @param flowType 流转类型 字典“流转类型”
     */
    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    /**
     * 情报派发单位是否下发：0未下发；1已下发。默认0；对应“报研判下发”步骤
     * @return IS_HANDOUT 情报派发单位是否下发：0未下发；1已下发。默认0；对应“报研判下发”步骤
     */
    public Integer getIsHandout() {
        return isHandout;
    }

    /**
     * 情报派发单位是否下发：0未下发；1已下发。默认0；对应“报研判下发”步骤
     * @param isHandout 情报派发单位是否下发：0未下发；1已下发。默认0；对应“报研判下发”步骤
     */
    public void setIsHandout(Integer isHandout) {
        this.isHandout = isHandout;
    }

    /**
     * 情报下发单位编码
     * @return HANDOUT_UNIT 情报下发单位编码
     */
    public String getHandoutUnit() {
        return handoutUnit;
    }

    /**
     * 情报下发单位编码
     * @param handoutUnit 情报下发单位编码
     */
    public void setHandoutUnit(String handoutUnit) {
        this.handoutUnit = handoutUnit == null ? null : handoutUnit.trim();
    }

    /**
     * 情报下发人员编码
     * @return HANDOUT_PERSON 情报下发人员编码
     */
    public String getHandoutPerson() {
        return handoutPerson;
    }

    /**
     * 情报下发人员编码
     * @param handoutPerson 情报下发人员编码
     */
    public void setHandoutPerson(String handoutPerson) {
        this.handoutPerson = handoutPerson == null ? null : handoutPerson.trim();
    }

    /**
     * 情报查证单位是否签收：0未签收；1已签收。默认0；对应“情报研判签收”步骤
     * @return IS_SIGN 情报查证单位是否签收：0未签收；1已签收。默认0；对应“情报研判签收”步骤
     */
    public Integer getIsSign() {
        return isSign;
    }

    /**
     * 情报查证单位是否签收：0未签收；1已签收。默认0；对应“情报研判签收”步骤
     * @param isSign 情报查证单位是否签收：0未签收；1已签收。默认0；对应“情报研判签收”步骤
     */
    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    /**
     * 情报签收单位编码
     * @return SIGN_UNIT 情报签收单位编码
     */
    public String getSignUnit() {
        return signUnit;
    }

    /**
     * 情报签收单位编码
     * @param signUnit 情报签收单位编码
     */
    public void setSignUnit(String signUnit) {
        this.signUnit = signUnit == null ? null : signUnit.trim();
    }

    /**
     * 情报签收人员编码
     * @return SIGN_PERSON 情报签收人员编码
     */
    public String getSignPerson() {
        return signPerson;
    }

    /**
     * 情报签收人员编码
     * @param signPerson 情报签收人员编码
     */
    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson == null ? null : signPerson.trim();
    }

    /**
     * 情报查证单位是否审核通过（接受）：0未接受；1接受。默认0；对应“情报签收审核”步骤
     * @return IS_ACCEPT 情报查证单位是否审核通过（接受）：0未接受；1接受。默认0；对应“情报签收审核”步骤
     */
    public Integer getIsAccept() {
        return isAccept;
    }

    /**
     * 情报查证单位是否审核通过（接受）：0未接受；1接受。默认0；对应“情报签收审核”步骤
     * @param isAccept 情报查证单位是否审核通过（接受）：0未接受；1接受。默认0；对应“情报签收审核”步骤
     */
    public void setIsAccept(Integer isAccept) {
        this.isAccept = isAccept;
    }

    /**
     * 情报接收审核单位编码
     * @return ACCEPT_UNIT 情报接收审核单位编码
     */
    public String getAcceptUnit() {
        return acceptUnit;
    }

    /**
     * 情报接收审核单位编码
     * @param acceptUnit 情报接收审核单位编码
     */
    public void setAcceptUnit(String acceptUnit) {
        this.acceptUnit = acceptUnit == null ? null : acceptUnit.trim();
    }

    /**
     * 情报接收审核人员编码
     * @return ACCEPT_PERSON 情报接收审核人员编码
     */
    public String getAcceptPerson() {
        return acceptPerson;
    }

    /**
     * 情报接收审核人员编码
     * @param acceptPerson 情报接收审核人员编码
     */
    public void setAcceptPerson(String acceptPerson) {
        this.acceptPerson = acceptPerson == null ? null : acceptPerson.trim();
    }

    /**
     * 情报查证单位查证完毕后是否上报：2未上报；1已上报。默认2；对应“情报研判上报”步骤
     * @return IS_REPORT 情报查证单位查证完毕后是否上报：2未上报；1已上报。默认2；对应“情报研判上报”步骤
     */
    public Integer getIsReport() {
        return isReport;
    }

    /**
     * 情报查证单位查证完毕后是否上报：0未上报；1已上报。默认0；对应“情报研判上报”步骤
     * @param isReport 情报查证单位查证完毕后是否上报：0未上报；1已上报。默认0；对应“情报研判上报”步骤
     */
    public void setIsReport(Integer isReport) {
        this.isReport = isReport;
    }

    /**
     * 研判上报单位编码
     * @return REPORT_UNIT 研判上报单位编码
     */
    public String getReportUnit() {
        return reportUnit;
    }

    /**
     * 研判上报单位编码
     * @param reportUnit 研判上报单位编码
     */
    public void setReportUnit(String reportUnit) {
        this.reportUnit = reportUnit == null ? null : reportUnit.trim();
    }

    /**
     * 研判上报人员编码
     * @return REPORT_PERSON 研判上报人员编码
     */
    public String getReportPerson() {
        return reportPerson;
    }

    /**
     * 研判上报人员编码
     * @param reportPerson 研判上报人员编码
     */
    public void setReportPerson(String reportPerson) {
        this.reportPerson = reportPerson == null ? null : reportPerson.trim();
    }

    /**
     * 情报派发单位审核上报是否通过：0不通过；1通过。默认0；对应 “情报研判审核”步骤
     * @return IS_VERIFY 情报派发单位审核上报是否通过：0不通过；1通过。默认0；对应 “情报研判审核”步骤
     */
    public Integer getIsVerify() {
        return isVerify;
    }

    /**
     * 情报派发单位审核上报是否通过：0不通过；1通过。默认0；对应 “情报研判审核”步骤
     * @param isVerify 情报派发单位审核上报是否通过：0不通过；1通过。默认0；对应 “情报研判审核”步骤
     */
    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    /**
     * 研判审核单位编码
     * @return VERIFY_UNIT 研判审核单位编码
     */
    public String getVerifyUnit() {
        return verifyUnit;
    }

    /**
     * 研判审核单位编码
     * @param verifyUnit 研判审核单位编码
     */
    public void setVerifyUnit(String verifyUnit) {
        this.verifyUnit = verifyUnit == null ? null : verifyUnit.trim();
    }

    /**
     * 研判审核人员编码
     * @return VERIFY_PERSON 研判审核人员编码
     */
    public String getVerifyPerson() {
        return verifyPerson;
    }

    /**
     * 研判审核人员编码
     * @param verifyPerson 研判审核人员编码
     */
    public void setVerifyPerson(String verifyPerson) {
        this.verifyPerson = verifyPerson == null ? null : verifyPerson.trim();
    }

    /**
     * 情报下发单位是否采用：0未采用；1已采用；默认0
     * @return IS_USE 情报下发单位是否采用：0未采用；1已采用；默认0
     */
    public Integer getIsUse() {
        return isUse;
    }

    /**
     * 情报下发单位是否采用：0未采用；1已采用；默认0
     * @param isUse 情报下发单位是否采用：0未采用；1已采用；默认0
     */
    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }
}