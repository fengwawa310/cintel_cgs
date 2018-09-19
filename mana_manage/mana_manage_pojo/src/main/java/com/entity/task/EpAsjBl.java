package com.entity.task;

import java.util.Date;

/**
 * 描述:ep_asj_bl表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-03-15
 */
public class EpAsjBl {
    /**
     * 主键
     */
    private String systemid;

    /**
     * 案件编号
     */
    private String ajbh;

    /**
     * 笔录序号
     */
    private Integer serNo;

    /**
     * 笔录名称
     */
    private String name;

    /**
     * 笔录类型，与B_ASJ_BL_AB关联
     */
    private String type;

    /**
     * 方案编号
     */
    private String scenSystemid;

    /**
     * 对象人员编号
     */
    private String target;

    /**
     * 0为未附卷，1为已附卷
     */
    private String fj;

    /**
     * 笔录开始时间
     */
    private Date starttime;

    /**
     * 笔录结束时间
     */
    private Date endtime;

    /**
     * 笔录纪录地点
     */
    private String jldd;

    /**
     * 笔录创建人姓名
     */
    private String creatorName;

    /**
     * 笔录创建人工作单位
     */
    private String createDept;

    /**
     * 记录笔录人姓名
     */
    private String recorder;

    /**
     * 记录笔录人工作单位
     */
    private String recorderDept;

    /**
     * 笔录对象姓名
     */
    private String targetXm;

    /**
     * 笔录对象曾用名
     */
    private String targetCym;

    /**
     * 笔录对象性别
     */
    private String targetXb;

    /**
     * 笔录对象生日
     */
    private Date targetCsrq;

    /**
     * 笔录对象文化程度
     */
    private String targetWhcd;

    /**
     * 笔录对象民族
     */
    private String targetMz;

    /**
     * 笔录对象户籍所在地
     */
    private String targetHjszd;

    /**
     * 笔录对象现住址
     */
    private String targetXzz;

    /**
     * 笔录对象证件种类
     */
    private String targetZjzl;

    /**
     * 笔录对象证件号码
     */
    private String targetZjhm;

    /**
     * 笔录对象工作单位
     */
    private String targetGzdw;

    /**
     * 笔录对象联系电话
     */
    private String targetLxdh;

    /**
     * 笔录对象到达时间
     */
    private Date targetArrivaltime;

    /**
     * 笔录对象离开时间
     */
    private Date targetLefttime;

    /**
     * 笔录对象别名
     */
    private String targetBm;

    /**
     * 笔录对象英文名
     */
    private String targetYwm;

    /**
     * 笔录对象国籍
     */
    private String targetGj;

    /**
     * 笔录对象绰号
     */
    private String targetCh;

    /**
     * 笔录对象籍贯
     */
    private String targetJg;

    /**
     * 笔录对象政治面貌
     */
    private String targetZzmm;

    /**
     * 笔录对象家庭情况
     */
    private String targetJtqk;

    /**
     * 笔录对象社会经历
     */
    private String targetShjl;

    /**
     * 笔录对象是否受过刑事行政处罚
     */
    private String targetSfsgcf;

    /**
     * 笔录对象是否聋哑人士
     */
    private String targetSflyrs;

    /**
     * 笔录对象是否配备翻译人员
     */
    private String targetSfpbfyry;

    /**
     * 翻译人员姓名
     */
    private String transXm;

    /**
     * 翻译人员职业
     */
    private String transZy;

    /**
     * 翻译人员工作单位
     */
    private String transGzdw;

    /**
     * 翻译人员现住址
     */
    private String transXzz;

    /**
     * 勘验地点/检查对象
     */
    private String kcdd;

    /**
     * 检查证或者工作证件号
     */
    private String jcz;

    /**
     * 勘验/检察人员姓名、工作单位、职务（职称）
     */
    private String kcryms;

    /**
     * 笔录对象职业
     */
    private String reservation01;

    /**
     * 保留字段02
     */
    private String reservation02;

    /**
     * 保留字段03
     */
    private String reservation03;

    /**
     * 保留字段04
     */
    private String reservation04;

    /**
     * 保留字段05
     */
    private String reservation05;

    /**
     * 保留字段06
     */
    private String reservation06;

    /**
     * 保留字段07
     */
    private Date reservation07;

    /**
     * 保留字段08
     */
    private Date reservation08;

    /**
     * 保留字段09
     */
    private Date reservation09;

    /**
     * 保留字段10
     */
    private Date reservation10;

    /**
     * 保留字段11
     */
    private Date reservation11;

    /**
     * 保留字段12
     */
    private Date reservation12;

    /**
     * 保留字段13
     */
    private String reservation13;

    /**
     * 保留字段14
     */
    private String reservation14;

    /**
     * 保留字段15
     */
    private String reservation15;

    /**
     * 保留字段16
     */
    private String reservation16;

    /**
     * 保留字段17
     */
    private String reservation17;

    /**
     * 保留字段18
     */
    private String reservation18;

    /**
     * 保留字段19
     */
    private String reservation19;

    /**
     * 保留字段20
     */
    private String reservation20;

    /**
     * 保留字段21
     */
    private String reservation21;

    /**
     * 保留字段22
     */
    private String reservation22;

    /**
     * 保留字段23
     */
    private String reservation23;

    /**
     * 保留字段24
     */
    private String reservation24;

    /**
     * 保留字段25
     */
    private String reservation25;

    /**
     * 保留字段26
     */
    private String reservation26;

    /**
     * 保留字段27
     */
    private String reservation27;

    /**
     * 保留字段28
     */
    private String reservation28;

    /**
     * 保留字段29
     */
    private String reservation29;

    /**
     * 保留字段30
     */
    private String reservation30;

    /**
     * 保留字段31
     */
    private String reservation31;

    /**
     * 保留字段32
     */
    private String reservation32;

    /**
     * 保留字段33
     */
    private String reservation33;

    /**
     * 保留字段34
     */
    private String reservation34;

    /**
     * 保留字段35
     */
    private String reservation35;

    /**
     * 保留字段36
     */
    private String reservation36;

    /**
     * 保留字段37
     */
    private String reservation37;

    /**
     * 保留字段38
     */
    private String reservation38;

    /**
     * 保留字段39
     */
    private String reservation39;

    /**
     * 保留字段40
     */
    private String reservation40;

    /**
     * 笔录创建人的部门编号
     */
    private String departmentcode;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createdtime;

    /**
     * 修改人
     */
    private String lastupdatedby;

    /**
     * 修改时间
     */
    private Date lastupdatedtime;

    /**
     * 上传标志
     */
    private String uploadflag;

    /**
     * 下载标志
     */
    private String downloadflag;

    /**
     * 删除标志
     */
    private String deleteflag;

    /**
     * 记录笔录的详细内容
     */
    private String body;

    /**
     * 主键
     * @return SYSTEMID 主键
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * 主键
     * @param systemid 主键
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }

    /**
     * 案件编号
     * @return AJBH 案件编号
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * 案件编号
     * @param ajbh 案件编号
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh == null ? null : ajbh.trim();
    }

    /**
     * 笔录序号
     * @return SER_NO 笔录序号
     */
    public Integer getSerNo() {
        return serNo;
    }

    /**
     * 笔录序号
     * @param serNo 笔录序号
     */
    public void setSerNo(Integer serNo) {
        this.serNo = serNo;
    }

    /**
     * 笔录名称
     * @return NAME 笔录名称
     */
    public String getName() {
        return name;
    }

    /**
     * 笔录名称
     * @param name 笔录名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 笔录类型，与B_ASJ_BL_AB关联
     * @return TYPE 笔录类型，与B_ASJ_BL_AB关联
     */
    public String getType() {
        return type;
    }

    /**
     * 笔录类型，与B_ASJ_BL_AB关联
     * @param type 笔录类型，与B_ASJ_BL_AB关联
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 方案编号
     * @return SCEN_SYSTEMID 方案编号
     */
    public String getScenSystemid() {
        return scenSystemid;
    }

    /**
     * 方案编号
     * @param scenSystemid 方案编号
     */
    public void setScenSystemid(String scenSystemid) {
        this.scenSystemid = scenSystemid == null ? null : scenSystemid.trim();
    }

    /**
     * 对象人员编号
     * @return TARGET 对象人员编号
     */
    public String getTarget() {
        return target;
    }

    /**
     * 对象人员编号
     * @param target 对象人员编号
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 0为未附卷，1为已附卷
     * @return FJ 0为未附卷，1为已附卷
     */
    public String getFj() {
        return fj;
    }

    /**
     * 0为未附卷，1为已附卷
     * @param fj 0为未附卷，1为已附卷
     */
    public void setFj(String fj) {
        this.fj = fj == null ? null : fj.trim();
    }

    /**
     * 笔录开始时间
     * @return STARTTIME 笔录开始时间
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * 笔录开始时间
     * @param starttime 笔录开始时间
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 笔录结束时间
     * @return ENDTIME 笔录结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 笔录结束时间
     * @param endtime 笔录结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 笔录纪录地点
     * @return JLDD 笔录纪录地点
     */
    public String getJldd() {
        return jldd;
    }

    /**
     * 笔录纪录地点
     * @param jldd 笔录纪录地点
     */
    public void setJldd(String jldd) {
        this.jldd = jldd == null ? null : jldd.trim();
    }

    /**
     * 笔录创建人姓名
     * @return CREATOR_NAME 笔录创建人姓名
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 笔录创建人姓名
     * @param creatorName 笔录创建人姓名
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    /**
     * 笔录创建人工作单位
     * @return CREATE_DEPT 笔录创建人工作单位
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 笔录创建人工作单位
     * @param createDept 笔录创建人工作单位
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 记录笔录人姓名
     * @return RECORDER 记录笔录人姓名
     */
    public String getRecorder() {
        return recorder;
    }

    /**
     * 记录笔录人姓名
     * @param recorder 记录笔录人姓名
     */
    public void setRecorder(String recorder) {
        this.recorder = recorder == null ? null : recorder.trim();
    }

    /**
     * 记录笔录人工作单位
     * @return RECORDER_DEPT 记录笔录人工作单位
     */
    public String getRecorderDept() {
        return recorderDept;
    }

    /**
     * 记录笔录人工作单位
     * @param recorderDept 记录笔录人工作单位
     */
    public void setRecorderDept(String recorderDept) {
        this.recorderDept = recorderDept == null ? null : recorderDept.trim();
    }

    /**
     * 笔录对象姓名
     * @return TARGET_XM 笔录对象姓名
     */
    public String getTargetXm() {
        return targetXm;
    }

    /**
     * 笔录对象姓名
     * @param targetXm 笔录对象姓名
     */
    public void setTargetXm(String targetXm) {
        this.targetXm = targetXm == null ? null : targetXm.trim();
    }

    /**
     * 笔录对象曾用名
     * @return TARGET_CYM 笔录对象曾用名
     */
    public String getTargetCym() {
        return targetCym;
    }

    /**
     * 笔录对象曾用名
     * @param targetCym 笔录对象曾用名
     */
    public void setTargetCym(String targetCym) {
        this.targetCym = targetCym == null ? null : targetCym.trim();
    }

    /**
     * 笔录对象性别
     * @return TARGET_XB 笔录对象性别
     */
    public String getTargetXb() {
        return targetXb;
    }

    /**
     * 笔录对象性别
     * @param targetXb 笔录对象性别
     */
    public void setTargetXb(String targetXb) {
        this.targetXb = targetXb == null ? null : targetXb.trim();
    }

    /**
     * 笔录对象生日
     * @return TARGET_CSRQ 笔录对象生日
     */
    public Date getTargetCsrq() {
        return targetCsrq;
    }

    /**
     * 笔录对象生日
     * @param targetCsrq 笔录对象生日
     */
    public void setTargetCsrq(Date targetCsrq) {
        this.targetCsrq = targetCsrq;
    }

    /**
     * 笔录对象文化程度
     * @return TARGET_WHCD 笔录对象文化程度
     */
    public String getTargetWhcd() {
        return targetWhcd;
    }

    /**
     * 笔录对象文化程度
     * @param targetWhcd 笔录对象文化程度
     */
    public void setTargetWhcd(String targetWhcd) {
        this.targetWhcd = targetWhcd == null ? null : targetWhcd.trim();
    }

    /**
     * 笔录对象民族
     * @return TARGET_MZ 笔录对象民族
     */
    public String getTargetMz() {
        return targetMz;
    }

    /**
     * 笔录对象民族
     * @param targetMz 笔录对象民族
     */
    public void setTargetMz(String targetMz) {
        this.targetMz = targetMz == null ? null : targetMz.trim();
    }

    /**
     * 笔录对象户籍所在地
     * @return TARGET_HJSZD 笔录对象户籍所在地
     */
    public String getTargetHjszd() {
        return targetHjszd;
    }

    /**
     * 笔录对象户籍所在地
     * @param targetHjszd 笔录对象户籍所在地
     */
    public void setTargetHjszd(String targetHjszd) {
        this.targetHjszd = targetHjszd == null ? null : targetHjszd.trim();
    }

    /**
     * 笔录对象现住址
     * @return TARGET_XZZ 笔录对象现住址
     */
    public String getTargetXzz() {
        return targetXzz;
    }

    /**
     * 笔录对象现住址
     * @param targetXzz 笔录对象现住址
     */
    public void setTargetXzz(String targetXzz) {
        this.targetXzz = targetXzz == null ? null : targetXzz.trim();
    }

    /**
     * 笔录对象证件种类
     * @return TARGET_ZJZL 笔录对象证件种类
     */
    public String getTargetZjzl() {
        return targetZjzl;
    }

    /**
     * 笔录对象证件种类
     * @param targetZjzl 笔录对象证件种类
     */
    public void setTargetZjzl(String targetZjzl) {
        this.targetZjzl = targetZjzl == null ? null : targetZjzl.trim();
    }

    /**
     * 笔录对象证件号码
     * @return TARGET_ZJHM 笔录对象证件号码
     */
    public String getTargetZjhm() {
        return targetZjhm;
    }

    /**
     * 笔录对象证件号码
     * @param targetZjhm 笔录对象证件号码
     */
    public void setTargetZjhm(String targetZjhm) {
        this.targetZjhm = targetZjhm == null ? null : targetZjhm.trim();
    }

    /**
     * 笔录对象工作单位
     * @return TARGET_GZDW 笔录对象工作单位
     */
    public String getTargetGzdw() {
        return targetGzdw;
    }

    /**
     * 笔录对象工作单位
     * @param targetGzdw 笔录对象工作单位
     */
    public void setTargetGzdw(String targetGzdw) {
        this.targetGzdw = targetGzdw == null ? null : targetGzdw.trim();
    }

    /**
     * 笔录对象联系电话
     * @return TARGET_LXDH 笔录对象联系电话
     */
    public String getTargetLxdh() {
        return targetLxdh;
    }

    /**
     * 笔录对象联系电话
     * @param targetLxdh 笔录对象联系电话
     */
    public void setTargetLxdh(String targetLxdh) {
        this.targetLxdh = targetLxdh == null ? null : targetLxdh.trim();
    }

    /**
     * 笔录对象到达时间
     * @return TARGET_ARRIVALTIME 笔录对象到达时间
     */
    public Date getTargetArrivaltime() {
        return targetArrivaltime;
    }

    /**
     * 笔录对象到达时间
     * @param targetArrivaltime 笔录对象到达时间
     */
    public void setTargetArrivaltime(Date targetArrivaltime) {
        this.targetArrivaltime = targetArrivaltime;
    }

    /**
     * 笔录对象离开时间
     * @return TARGET_LEFTTIME 笔录对象离开时间
     */
    public Date getTargetLefttime() {
        return targetLefttime;
    }

    /**
     * 笔录对象离开时间
     * @param targetLefttime 笔录对象离开时间
     */
    public void setTargetLefttime(Date targetLefttime) {
        this.targetLefttime = targetLefttime;
    }

    /**
     * 笔录对象别名
     * @return TARGET_BM 笔录对象别名
     */
    public String getTargetBm() {
        return targetBm;
    }

    /**
     * 笔录对象别名
     * @param targetBm 笔录对象别名
     */
    public void setTargetBm(String targetBm) {
        this.targetBm = targetBm == null ? null : targetBm.trim();
    }

    /**
     * 笔录对象英文名
     * @return TARGET_YWM 笔录对象英文名
     */
    public String getTargetYwm() {
        return targetYwm;
    }

    /**
     * 笔录对象英文名
     * @param targetYwm 笔录对象英文名
     */
    public void setTargetYwm(String targetYwm) {
        this.targetYwm = targetYwm == null ? null : targetYwm.trim();
    }

    /**
     * 笔录对象国籍
     * @return TARGET_GJ 笔录对象国籍
     */
    public String getTargetGj() {
        return targetGj;
    }

    /**
     * 笔录对象国籍
     * @param targetGj 笔录对象国籍
     */
    public void setTargetGj(String targetGj) {
        this.targetGj = targetGj == null ? null : targetGj.trim();
    }

    /**
     * 笔录对象绰号
     * @return TARGET_CH 笔录对象绰号
     */
    public String getTargetCh() {
        return targetCh;
    }

    /**
     * 笔录对象绰号
     * @param targetCh 笔录对象绰号
     */
    public void setTargetCh(String targetCh) {
        this.targetCh = targetCh == null ? null : targetCh.trim();
    }

    /**
     * 笔录对象籍贯
     * @return TARGET_JG 笔录对象籍贯
     */
    public String getTargetJg() {
        return targetJg;
    }

    /**
     * 笔录对象籍贯
     * @param targetJg 笔录对象籍贯
     */
    public void setTargetJg(String targetJg) {
        this.targetJg = targetJg == null ? null : targetJg.trim();
    }

    /**
     * 笔录对象政治面貌
     * @return TARGET_ZZMM 笔录对象政治面貌
     */
    public String getTargetZzmm() {
        return targetZzmm;
    }

    /**
     * 笔录对象政治面貌
     * @param targetZzmm 笔录对象政治面貌
     */
    public void setTargetZzmm(String targetZzmm) {
        this.targetZzmm = targetZzmm == null ? null : targetZzmm.trim();
    }

    /**
     * 笔录对象家庭情况
     * @return TARGET_JTQK 笔录对象家庭情况
     */
    public String getTargetJtqk() {
        return targetJtqk;
    }

    /**
     * 笔录对象家庭情况
     * @param targetJtqk 笔录对象家庭情况
     */
    public void setTargetJtqk(String targetJtqk) {
        this.targetJtqk = targetJtqk == null ? null : targetJtqk.trim();
    }

    /**
     * 笔录对象社会经历
     * @return TARGET_SHJL 笔录对象社会经历
     */
    public String getTargetShjl() {
        return targetShjl;
    }

    /**
     * 笔录对象社会经历
     * @param targetShjl 笔录对象社会经历
     */
    public void setTargetShjl(String targetShjl) {
        this.targetShjl = targetShjl == null ? null : targetShjl.trim();
    }

    /**
     * 笔录对象是否受过刑事行政处罚
     * @return TARGET_SFSGCF 笔录对象是否受过刑事行政处罚
     */
    public String getTargetSfsgcf() {
        return targetSfsgcf;
    }

    /**
     * 笔录对象是否受过刑事行政处罚
     * @param targetSfsgcf 笔录对象是否受过刑事行政处罚
     */
    public void setTargetSfsgcf(String targetSfsgcf) {
        this.targetSfsgcf = targetSfsgcf == null ? null : targetSfsgcf.trim();
    }

    /**
     * 笔录对象是否聋哑人士
     * @return TARGET_SFLYRS 笔录对象是否聋哑人士
     */
    public String getTargetSflyrs() {
        return targetSflyrs;
    }

    /**
     * 笔录对象是否聋哑人士
     * @param targetSflyrs 笔录对象是否聋哑人士
     */
    public void setTargetSflyrs(String targetSflyrs) {
        this.targetSflyrs = targetSflyrs == null ? null : targetSflyrs.trim();
    }

    /**
     * 笔录对象是否配备翻译人员
     * @return TARGET_SFPBFYRY 笔录对象是否配备翻译人员
     */
    public String getTargetSfpbfyry() {
        return targetSfpbfyry;
    }

    /**
     * 笔录对象是否配备翻译人员
     * @param targetSfpbfyry 笔录对象是否配备翻译人员
     */
    public void setTargetSfpbfyry(String targetSfpbfyry) {
        this.targetSfpbfyry = targetSfpbfyry == null ? null : targetSfpbfyry.trim();
    }

    /**
     * 翻译人员姓名
     * @return TRANS_XM 翻译人员姓名
     */
    public String getTransXm() {
        return transXm;
    }

    /**
     * 翻译人员姓名
     * @param transXm 翻译人员姓名
     */
    public void setTransXm(String transXm) {
        this.transXm = transXm == null ? null : transXm.trim();
    }

    /**
     * 翻译人员职业
     * @return TRANS_ZY 翻译人员职业
     */
    public String getTransZy() {
        return transZy;
    }

    /**
     * 翻译人员职业
     * @param transZy 翻译人员职业
     */
    public void setTransZy(String transZy) {
        this.transZy = transZy == null ? null : transZy.trim();
    }

    /**
     * 翻译人员工作单位
     * @return TRANS_GZDW 翻译人员工作单位
     */
    public String getTransGzdw() {
        return transGzdw;
    }

    /**
     * 翻译人员工作单位
     * @param transGzdw 翻译人员工作单位
     */
    public void setTransGzdw(String transGzdw) {
        this.transGzdw = transGzdw == null ? null : transGzdw.trim();
    }

    /**
     * 翻译人员现住址
     * @return TRANS_XZZ 翻译人员现住址
     */
    public String getTransXzz() {
        return transXzz;
    }

    /**
     * 翻译人员现住址
     * @param transXzz 翻译人员现住址
     */
    public void setTransXzz(String transXzz) {
        this.transXzz = transXzz == null ? null : transXzz.trim();
    }

    /**
     * 勘验地点/检查对象
     * @return KCDD 勘验地点/检查对象
     */
    public String getKcdd() {
        return kcdd;
    }

    /**
     * 勘验地点/检查对象
     * @param kcdd 勘验地点/检查对象
     */
    public void setKcdd(String kcdd) {
        this.kcdd = kcdd == null ? null : kcdd.trim();
    }

    /**
     * 检查证或者工作证件号
     * @return JCZ 检查证或者工作证件号
     */
    public String getJcz() {
        return jcz;
    }

    /**
     * 检查证或者工作证件号
     * @param jcz 检查证或者工作证件号
     */
    public void setJcz(String jcz) {
        this.jcz = jcz == null ? null : jcz.trim();
    }

    /**
     * 勘验/检察人员姓名、工作单位、职务（职称）
     * @return KCRYMS 勘验/检察人员姓名、工作单位、职务（职称）
     */
    public String getKcryms() {
        return kcryms;
    }

    /**
     * 勘验/检察人员姓名、工作单位、职务（职称）
     * @param kcryms 勘验/检察人员姓名、工作单位、职务（职称）
     */
    public void setKcryms(String kcryms) {
        this.kcryms = kcryms == null ? null : kcryms.trim();
    }

    /**
     * 笔录对象职业
     * @return RESERVATION01 笔录对象职业
     */
    public String getReservation01() {
        return reservation01;
    }

    /**
     * 笔录对象职业
     * @param reservation01 笔录对象职业
     */
    public void setReservation01(String reservation01) {
        this.reservation01 = reservation01 == null ? null : reservation01.trim();
    }

    /**
     * 保留字段02
     * @return RESERVATION02 保留字段02
     */
    public String getReservation02() {
        return reservation02;
    }

    /**
     * 保留字段02
     * @param reservation02 保留字段02
     */
    public void setReservation02(String reservation02) {
        this.reservation02 = reservation02 == null ? null : reservation02.trim();
    }

    /**
     * 保留字段03
     * @return RESERVATION03 保留字段03
     */
    public String getReservation03() {
        return reservation03;
    }

    /**
     * 保留字段03
     * @param reservation03 保留字段03
     */
    public void setReservation03(String reservation03) {
        this.reservation03 = reservation03 == null ? null : reservation03.trim();
    }

    /**
     * 保留字段04
     * @return RESERVATION04 保留字段04
     */
    public String getReservation04() {
        return reservation04;
    }

    /**
     * 保留字段04
     * @param reservation04 保留字段04
     */
    public void setReservation04(String reservation04) {
        this.reservation04 = reservation04 == null ? null : reservation04.trim();
    }

    /**
     * 保留字段05
     * @return RESERVATION05 保留字段05
     */
    public String getReservation05() {
        return reservation05;
    }

    /**
     * 保留字段05
     * @param reservation05 保留字段05
     */
    public void setReservation05(String reservation05) {
        this.reservation05 = reservation05 == null ? null : reservation05.trim();
    }

    /**
     * 保留字段06
     * @return RESERVATION06 保留字段06
     */
    public String getReservation06() {
        return reservation06;
    }

    /**
     * 保留字段06
     * @param reservation06 保留字段06
     */
    public void setReservation06(String reservation06) {
        this.reservation06 = reservation06 == null ? null : reservation06.trim();
    }

    /**
     * 保留字段07
     * @return RESERVATION07 保留字段07
     */
    public Date getReservation07() {
        return reservation07;
    }

    /**
     * 保留字段07
     * @param reservation07 保留字段07
     */
    public void setReservation07(Date reservation07) {
        this.reservation07 = reservation07;
    }

    /**
     * 保留字段08
     * @return RESERVATION08 保留字段08
     */
    public Date getReservation08() {
        return reservation08;
    }

    /**
     * 保留字段08
     * @param reservation08 保留字段08
     */
    public void setReservation08(Date reservation08) {
        this.reservation08 = reservation08;
    }

    /**
     * 保留字段09
     * @return RESERVATION09 保留字段09
     */
    public Date getReservation09() {
        return reservation09;
    }

    /**
     * 保留字段09
     * @param reservation09 保留字段09
     */
    public void setReservation09(Date reservation09) {
        this.reservation09 = reservation09;
    }

    /**
     * 保留字段10
     * @return RESERVATION10 保留字段10
     */
    public Date getReservation10() {
        return reservation10;
    }

    /**
     * 保留字段10
     * @param reservation10 保留字段10
     */
    public void setReservation10(Date reservation10) {
        this.reservation10 = reservation10;
    }

    /**
     * 保留字段11
     * @return RESERVATION11 保留字段11
     */
    public Date getReservation11() {
        return reservation11;
    }

    /**
     * 保留字段11
     * @param reservation11 保留字段11
     */
    public void setReservation11(Date reservation11) {
        this.reservation11 = reservation11;
    }

    /**
     * 保留字段12
     * @return RESERVATION12 保留字段12
     */
    public Date getReservation12() {
        return reservation12;
    }

    /**
     * 保留字段12
     * @param reservation12 保留字段12
     */
    public void setReservation12(Date reservation12) {
        this.reservation12 = reservation12;
    }

    /**
     * 保留字段13
     * @return RESERVATION13 保留字段13
     */
    public String getReservation13() {
        return reservation13;
    }

    /**
     * 保留字段13
     * @param reservation13 保留字段13
     */
    public void setReservation13(String reservation13) {
        this.reservation13 = reservation13 == null ? null : reservation13.trim();
    }

    /**
     * 保留字段14
     * @return RESERVATION14 保留字段14
     */
    public String getReservation14() {
        return reservation14;
    }

    /**
     * 保留字段14
     * @param reservation14 保留字段14
     */
    public void setReservation14(String reservation14) {
        this.reservation14 = reservation14 == null ? null : reservation14.trim();
    }

    /**
     * 保留字段15
     * @return RESERVATION15 保留字段15
     */
    public String getReservation15() {
        return reservation15;
    }

    /**
     * 保留字段15
     * @param reservation15 保留字段15
     */
    public void setReservation15(String reservation15) {
        this.reservation15 = reservation15 == null ? null : reservation15.trim();
    }

    /**
     * 保留字段16
     * @return RESERVATION16 保留字段16
     */
    public String getReservation16() {
        return reservation16;
    }

    /**
     * 保留字段16
     * @param reservation16 保留字段16
     */
    public void setReservation16(String reservation16) {
        this.reservation16 = reservation16 == null ? null : reservation16.trim();
    }

    /**
     * 保留字段17
     * @return RESERVATION17 保留字段17
     */
    public String getReservation17() {
        return reservation17;
    }

    /**
     * 保留字段17
     * @param reservation17 保留字段17
     */
    public void setReservation17(String reservation17) {
        this.reservation17 = reservation17 == null ? null : reservation17.trim();
    }

    /**
     * 保留字段18
     * @return RESERVATION18 保留字段18
     */
    public String getReservation18() {
        return reservation18;
    }

    /**
     * 保留字段18
     * @param reservation18 保留字段18
     */
    public void setReservation18(String reservation18) {
        this.reservation18 = reservation18 == null ? null : reservation18.trim();
    }

    /**
     * 保留字段19
     * @return RESERVATION19 保留字段19
     */
    public String getReservation19() {
        return reservation19;
    }

    /**
     * 保留字段19
     * @param reservation19 保留字段19
     */
    public void setReservation19(String reservation19) {
        this.reservation19 = reservation19 == null ? null : reservation19.trim();
    }

    /**
     * 保留字段20
     * @return RESERVATION20 保留字段20
     */
    public String getReservation20() {
        return reservation20;
    }

    /**
     * 保留字段20
     * @param reservation20 保留字段20
     */
    public void setReservation20(String reservation20) {
        this.reservation20 = reservation20 == null ? null : reservation20.trim();
    }

    /**
     * 保留字段21
     * @return RESERVATION21 保留字段21
     */
    public String getReservation21() {
        return reservation21;
    }

    /**
     * 保留字段21
     * @param reservation21 保留字段21
     */
    public void setReservation21(String reservation21) {
        this.reservation21 = reservation21 == null ? null : reservation21.trim();
    }

    /**
     * 保留字段22
     * @return RESERVATION22 保留字段22
     */
    public String getReservation22() {
        return reservation22;
    }

    /**
     * 保留字段22
     * @param reservation22 保留字段22
     */
    public void setReservation22(String reservation22) {
        this.reservation22 = reservation22 == null ? null : reservation22.trim();
    }

    /**
     * 保留字段23
     * @return RESERVATION23 保留字段23
     */
    public String getReservation23() {
        return reservation23;
    }

    /**
     * 保留字段23
     * @param reservation23 保留字段23
     */
    public void setReservation23(String reservation23) {
        this.reservation23 = reservation23 == null ? null : reservation23.trim();
    }

    /**
     * 保留字段24
     * @return RESERVATION24 保留字段24
     */
    public String getReservation24() {
        return reservation24;
    }

    /**
     * 保留字段24
     * @param reservation24 保留字段24
     */
    public void setReservation24(String reservation24) {
        this.reservation24 = reservation24 == null ? null : reservation24.trim();
    }

    /**
     * 保留字段25
     * @return RESERVATION25 保留字段25
     */
    public String getReservation25() {
        return reservation25;
    }

    /**
     * 保留字段25
     * @param reservation25 保留字段25
     */
    public void setReservation25(String reservation25) {
        this.reservation25 = reservation25 == null ? null : reservation25.trim();
    }

    /**
     * 保留字段26
     * @return RESERVATION26 保留字段26
     */
    public String getReservation26() {
        return reservation26;
    }

    /**
     * 保留字段26
     * @param reservation26 保留字段26
     */
    public void setReservation26(String reservation26) {
        this.reservation26 = reservation26 == null ? null : reservation26.trim();
    }

    /**
     * 保留字段27
     * @return RESERVATION27 保留字段27
     */
    public String getReservation27() {
        return reservation27;
    }

    /**
     * 保留字段27
     * @param reservation27 保留字段27
     */
    public void setReservation27(String reservation27) {
        this.reservation27 = reservation27 == null ? null : reservation27.trim();
    }

    /**
     * 保留字段28
     * @return RESERVATION28 保留字段28
     */
    public String getReservation28() {
        return reservation28;
    }

    /**
     * 保留字段28
     * @param reservation28 保留字段28
     */
    public void setReservation28(String reservation28) {
        this.reservation28 = reservation28 == null ? null : reservation28.trim();
    }

    /**
     * 保留字段29
     * @return RESERVATION29 保留字段29
     */
    public String getReservation29() {
        return reservation29;
    }

    /**
     * 保留字段29
     * @param reservation29 保留字段29
     */
    public void setReservation29(String reservation29) {
        this.reservation29 = reservation29 == null ? null : reservation29.trim();
    }

    /**
     * 保留字段30
     * @return RESERVATION30 保留字段30
     */
    public String getReservation30() {
        return reservation30;
    }

    /**
     * 保留字段30
     * @param reservation30 保留字段30
     */
    public void setReservation30(String reservation30) {
        this.reservation30 = reservation30 == null ? null : reservation30.trim();
    }

    /**
     * 保留字段31
     * @return RESERVATION31 保留字段31
     */
    public String getReservation31() {
        return reservation31;
    }

    /**
     * 保留字段31
     * @param reservation31 保留字段31
     */
    public void setReservation31(String reservation31) {
        this.reservation31 = reservation31 == null ? null : reservation31.trim();
    }

    /**
     * 保留字段32
     * @return RESERVATION32 保留字段32
     */
    public String getReservation32() {
        return reservation32;
    }

    /**
     * 保留字段32
     * @param reservation32 保留字段32
     */
    public void setReservation32(String reservation32) {
        this.reservation32 = reservation32 == null ? null : reservation32.trim();
    }

    /**
     * 保留字段33
     * @return RESERVATION33 保留字段33
     */
    public String getReservation33() {
        return reservation33;
    }

    /**
     * 保留字段33
     * @param reservation33 保留字段33
     */
    public void setReservation33(String reservation33) {
        this.reservation33 = reservation33 == null ? null : reservation33.trim();
    }

    /**
     * 保留字段34
     * @return RESERVATION34 保留字段34
     */
    public String getReservation34() {
        return reservation34;
    }

    /**
     * 保留字段34
     * @param reservation34 保留字段34
     */
    public void setReservation34(String reservation34) {
        this.reservation34 = reservation34 == null ? null : reservation34.trim();
    }

    /**
     * 保留字段35
     * @return RESERVATION35 保留字段35
     */
    public String getReservation35() {
        return reservation35;
    }

    /**
     * 保留字段35
     * @param reservation35 保留字段35
     */
    public void setReservation35(String reservation35) {
        this.reservation35 = reservation35 == null ? null : reservation35.trim();
    }

    /**
     * 保留字段36
     * @return RESERVATION36 保留字段36
     */
    public String getReservation36() {
        return reservation36;
    }

    /**
     * 保留字段36
     * @param reservation36 保留字段36
     */
    public void setReservation36(String reservation36) {
        this.reservation36 = reservation36 == null ? null : reservation36.trim();
    }

    /**
     * 保留字段37
     * @return RESERVATION37 保留字段37
     */
    public String getReservation37() {
        return reservation37;
    }

    /**
     * 保留字段37
     * @param reservation37 保留字段37
     */
    public void setReservation37(String reservation37) {
        this.reservation37 = reservation37 == null ? null : reservation37.trim();
    }

    /**
     * 保留字段38
     * @return RESERVATION38 保留字段38
     */
    public String getReservation38() {
        return reservation38;
    }

    /**
     * 保留字段38
     * @param reservation38 保留字段38
     */
    public void setReservation38(String reservation38) {
        this.reservation38 = reservation38 == null ? null : reservation38.trim();
    }

    /**
     * 保留字段39
     * @return RESERVATION39 保留字段39
     */
    public String getReservation39() {
        return reservation39;
    }

    /**
     * 保留字段39
     * @param reservation39 保留字段39
     */
    public void setReservation39(String reservation39) {
        this.reservation39 = reservation39 == null ? null : reservation39.trim();
    }

    /**
     * 保留字段40
     * @return RESERVATION40 保留字段40
     */
    public String getReservation40() {
        return reservation40;
    }

    /**
     * 保留字段40
     * @param reservation40 保留字段40
     */
    public void setReservation40(String reservation40) {
        this.reservation40 = reservation40 == null ? null : reservation40.trim();
    }

    /**
     * 笔录创建人的部门编号
     * @return DEPARTMENTCODE 笔录创建人的部门编号
     */
    public String getDepartmentcode() {
        return departmentcode;
    }

    /**
     * 笔录创建人的部门编号
     * @param departmentcode 笔录创建人的部门编号
     */
    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode == null ? null : departmentcode.trim();
    }

    /**
     * 创建人
     * @return CREATOR 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return CREATEDTIME 创建时间
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * 创建时间
     * @param createdtime 创建时间
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * 修改人
     * @return LASTUPDATEDBY 修改人
     */
    public String getLastupdatedby() {
        return lastupdatedby;
    }

    /**
     * 修改人
     * @param lastupdatedby 修改人
     */
    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby == null ? null : lastupdatedby.trim();
    }

    /**
     * 修改时间
     * @return LASTUPDATEDTIME 修改时间
     */
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * 修改时间
     * @param lastupdatedtime 修改时间
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * 上传标志
     * @return UPLOADFLAG 上传标志
     */
    public String getUploadflag() {
        return uploadflag;
    }

    /**
     * 上传标志
     * @param uploadflag 上传标志
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag == null ? null : uploadflag.trim();
    }

    /**
     * 下载标志
     * @return DOWNLOADFLAG 下载标志
     */
    public String getDownloadflag() {
        return downloadflag;
    }

    /**
     * 下载标志
     * @param downloadflag 下载标志
     */
    public void setDownloadflag(String downloadflag) {
        this.downloadflag = downloadflag == null ? null : downloadflag.trim();
    }

    /**
     * 删除标志
     * @return DELETEFLAG 删除标志
     */
    public String getDeleteflag() {
        return deleteflag;
    }

    /**
     * 删除标志
     * @param deleteflag 删除标志
     */
    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag == null ? null : deleteflag.trim();
    }

    /**
     * 记录笔录的详细内容
     * @return BODY 记录笔录的详细内容
     */
    public String getBody() {
        return body;
    }

    /**
     * 记录笔录的详细内容
     * @param body 记录笔录的详细内容
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }
}