package cn.yat.myentity;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class LogEntity {
    private Map<Integer , List<LogDataSourceEntity>> map = Maps.newHashMap();

    public Map<Integer, List<LogDataSourceEntity>> getMap() {
        return map;
    }

    public void setMap(Map<Integer, List<LogDataSourceEntity>> map) {
        this.map = map;
    }
}