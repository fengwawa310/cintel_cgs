package com.entity.task;

import java.math.BigDecimal;

public class NiandudaocaojihuaEntity {
    private String id;

    private String productYear;

    private String round;

    private String wsId;

    private String teamId;

    private BigDecimal planSk;

    private BigDecimal planMk;

    private BigDecimal planStraw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductYear() {
        return productYear;
    }

    public void setProductYear(String productYear) {
        this.productYear = productYear == null ? null : productYear.trim();
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId == null ? null : wsId.trim();
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public BigDecimal getPlanSk() {
        return planSk;
    }

    public void setPlanSk(BigDecimal planSk) {
        this.planSk = planSk;
    }

    public BigDecimal getPlanMk() {
        return planMk;
    }

    public void setPlanMk(BigDecimal planMk) {
        this.planMk = planMk;
    }

    public BigDecimal getPlanStraw() {
        return planStraw;
    }

    public void setPlanStraw(BigDecimal planStraw) {
        this.planStraw = planStraw;
    }
}