package com.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sky on 2017/12/16.
 */
public class CountDuplicatedList {

    public static String getMaxDuplicate(ArrayList<String> list){

        String maxDuplicated = null;

        if (!(list.isEmpty() || list.size() == 0)){
            Map<String,Integer> map = new HashMap();
            for (String temp : list){
                Integer count = map.get(temp);
                map.put(temp,(count == null) ? 1 : count + 1);
            }
            TreeMap<Integer,String> treeMap = new TreeMap<>();
            for (Map.Entry<String,Integer> entry : map.entrySet()){
                treeMap.put(entry.getValue(),entry.getKey());
            }

            maxDuplicated = treeMap.get(treeMap.lastKey());
        }else {
            maxDuplicated = "";
        }

        return maxDuplicated;
    }

}
