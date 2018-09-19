package com.service.task.helper;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T> 转变后的数据模型
 * @param <K> 源数据模型
 */
public abstract class JzToSrcHelper<T,K> {
    public  List<T> transTheJzToSrc(List<K> jzData,Class<T> clazz) {
        if (jzData == null || jzData.isEmpty()) {
            return null;
        }
        List<T> srcData = new ArrayList<>();
        for (int i = 0; i < jzData.size(); i++) {
            K jzEndity = jzData.get(i);
            T srcEntity = transTheJzToSrc(jzEndity,clazz);
            if (srcEntity == null) {
                continue;
            }
            srcData.add(srcEntity);
        }
        return srcData;
    }

    public T transTheJzToSrc(K jzEndity,Class<T> clazz) {
        if (jzEndity == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(obj == null)
        {
            return null;
        }
        BeanUtils.copyProperties(jzEndity, obj);
        obj = whenTransOver(obj,jzEndity);
        return obj;
    }
    public abstract T whenTransOver(T objT,K objK);
}
