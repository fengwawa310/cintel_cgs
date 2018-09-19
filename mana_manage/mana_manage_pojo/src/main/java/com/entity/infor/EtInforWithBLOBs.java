package com.entity.infor;

public class EtInforWithBLOBs extends EtInfor {
    /**
     * 需查证内容
     */
    private String inforContent;

    /**
     * 查证工作建议
     */
    private String advise;

    /**
     * 查证过程
     */
    private String process;

    /**
     * 查证结果
     */
    private String result;

    /**
     * 需查证内容
     * @return INFOR_CONTENT 需查证内容
     */
    public String getInforContent() {
        return inforContent;
    }

    /**
     * 需查证内容
     * @param inforContent 需查证内容
     */
    public void setInforContent(String inforContent) {
        this.inforContent = inforContent == null ? null : inforContent.trim();
    }

    /**
     * 查证工作建议
     * @return ADVISE 查证工作建议
     */
    public String getAdvise() {
        return advise;
    }

    /**
     * 查证工作建议
     * @param advise 查证工作建议
     */
    public void setAdvise(String advise) {
        this.advise = advise == null ? null : advise.trim();
    }

    /**
     * 查证过程
     * @return PROCESS 查证过程
     */
    public String getProcess() {
        return process;
    }

    /**
     * 查证过程
     * @param process 查证过程
     */
    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    /**
     * 查证结果
     * @return RESULT 查证结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 查证结果
     * @param result 查证结果
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}