package com.collect.cnf;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "taskcnf")
public class CTTaskCnf
{

    /**
     * AJ 案件数据 采集任务标识
     */
    public final static String CT_AJ = "AJ";

    /**
     * JQ 警情数据 采集任务标识
     */
    public final static String CT_JQ = "JQ";

    /**
     * BL 笔录数据 采集任务标识
     */
    public final static String CT_BL = "BL";

    /**
     * WP 物品数据 采集任务标识
     */
    public final static String CT_WP = "WP";

    /**
     * RYQK 嫌疑人与案件关联关系数据 采集任务标识
     */
    public final static String CT_RYQK = "RYQK";

    /**
     * XYR 嫌疑人数据 采集任务标识
     */
    public final static String CT_XYR = "XYR";

    /**
     * 配置文件名称
     */
    public final static String CNF_FILENAME = "CollectTaskCnf.xml";

    @XmlElements(value =
    { @XmlElement(name = "cnf", type = CTCnf.class) })
    private List<CTCnf> cnfs;

    @XmlTransient
    public List<CTCnf> getCnfs()
    {
        return cnfs;
    }

    public void setCnfs(List<CTCnf> cnfs)
    {
        this.cnfs = cnfs;
    }

}
