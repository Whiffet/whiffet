package org.hadoop.whiffet.common.util;


import java.util.Map;

import java.util.TreeMap;

public class MapSorter {

    /**
     * 按key排序
     * @param map
     * @return
     */
    public static Map<?,?> sortByKey(Map<?,?> map) {
        Map<?, ?> mapVK = new TreeMap<Object, Object>(map);
        return mapVK;
    }
   
}
