package com.entity;

/**
 * @Author: sky
 * @Description:
 * @Date: Create in 下午7:33 2018/4/2
 */
public enum TagEnum {

    SAME_ROOM("同房间",1),SAME_DAY_IN("同入住",2),SAME_DAY_GO("同离开",3),
    SAME_HOTEL("同酒店",4),SAME_NEAR_HOTEL("同附近酒店",5),SAME_GANG("同团伙",6),
    SAME_CASE("同案件",7),SAME_ALARM("同警情",8);

    // 成员变量
    private String tag;

    private int index;

    TagEnum(String tag, int index) {
        this.tag = tag;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (TagEnum c : TagEnum.values()) {
            if (c.getIndex() == index) {
                return c.tag;
            }
        }
        return null;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
