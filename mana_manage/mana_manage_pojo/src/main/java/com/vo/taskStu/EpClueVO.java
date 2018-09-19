package com.vo.taskStu;

import com.common.enums.EnumTypeVO;
import com.entity.xmlparse.EpClue;

/**
 * @author admin
 * @create 2018-03-16 15:01
 **/
public class EpClueVO extends EpClue {

    private EnumTypeVO typeEnum;//线索类型枚举


    public EnumTypeVO getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(EnumTypeVO typeEnum) {
        this.typeEnum = typeEnum;
    }
}
