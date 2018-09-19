package com.vo.taskStu;

import com.common.enums.EnumTypeVO;
import com.entity.taskStu.EtJudgeflow;

import java.util.Date;

/**
 * 描述:et_judgeflow表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-03-06
 */
public class EtJudgeflowVo extends EtJudgeflow {

    /*审核状态*/
    private EnumTypeVO shenHeState ;
    /*签收状态*/
    private EnumTypeVO qianshouState ;
    /*研判状态*/
    private EnumTypeVO yanpanState ;

    public EnumTypeVO getShenHeState() {
        return shenHeState;
    }

    public void setShenHeState(EnumTypeVO shenHeState) {
        this.shenHeState = shenHeState;
    }

    public EnumTypeVO getQianshouState() {
        return qianshouState;
    }

    public void setQianshouState(EnumTypeVO qianshouState) {
        this.qianshouState = qianshouState;
    }

    public EnumTypeVO getYanpanState() {
        return yanpanState;
    }

    public void setYanpanState(EnumTypeVO yanpanState) {
        this.yanpanState = yanpanState;
    }



    public EtJudgeflowVo(String id, String caseNo, Integer judgeType, String title, Integer flowState, String auditorUnit, String auditorCode, String auditOpinion, Date auditTime, String issuedUnit, String issuterCode, Date issuedTime, String signUnit, String signCode, String signOpinion, Date signTime, String judgeCode, String judgeOpinion, Date judgeTime, Date creatTime, Date modifyTime) {
        super(id, caseNo, judgeType, title, flowState, auditorUnit, auditorCode, auditOpinion, auditTime, issuedUnit, issuterCode, issuedTime, signUnit, signCode, signOpinion, signTime, judgeCode, judgeOpinion, judgeTime, creatTime, modifyTime);
    }

    public static EtJudgeflowVo ValueOf(EtJudgeflow etJudgeflow) {
        EtJudgeflowVo etJudgeflowVo = new EtJudgeflowVo(
                etJudgeflow.getId(),
                etJudgeflow.getCaseNo(),
                etJudgeflow.getJudgeType(),
                etJudgeflow.getTitle(),
                etJudgeflow.getFlowState(),
                etJudgeflow.getAuditorUnit(),
                etJudgeflow.getAuditorCode(),
                etJudgeflow.getAuditOpinion(),
                etJudgeflow.getAuditTime(),
                etJudgeflow.getIssuedUnit(),
                etJudgeflow.getIssuterCode(),
                etJudgeflow.getIssuedTime(),
                etJudgeflow.getSignUnit(),
                etJudgeflow.getSignCode(),
                etJudgeflow.getSignOpinion(),
                etJudgeflow.getSignTime(),
                etJudgeflow.getJudgeCode(),
                etJudgeflow.getJudgeOpinion(),
                etJudgeflow.getJudgeTime(),
                etJudgeflow.getCreatTime(),
                etJudgeflow.getModifyTime()
        );
        return etJudgeflowVo;
    }
}