package com.collect.ANJQ.task;

import java.util.List;
import java.util.Map;

/**
 * Created by Auri on 2018/2/10.
 * Desc:分页采集监听器
 */
public interface CollectTaskLisnr {
    /**
     * 当一次分页采集结束时触发
     *
     * @param params
     */
    void whenPageCollectFinish(Map<String,List<String>> params);
}
