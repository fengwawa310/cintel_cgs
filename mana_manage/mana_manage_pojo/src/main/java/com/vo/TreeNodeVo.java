package com.vo;

/**
 * Created by Auri on 2018/2/3.
 * Desc: 通用树形节点
 */
public class TreeNodeVo implements Comparable<String> {

    public static final int TYPE_DIR = 0;

    public static final int TYPE_NODE = 1;


    /**
     * 当前节点ID
     */
    protected String id;

    /**
     * 父级节点ID
     */
    protected String pId;

    /**
     * 展示名称
     */
    protected String name;

    /**
     * 是否展开
     */
    protected boolean open;

    /**
     * 树形节点类型 0：文件夹 1：重点人员
     */
    protected int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int compareTo(String o) {
        int temp = this.id.compareTo(o) > 0 ? 1 : -1;
        return temp;
    }
}
