package com.qiancijun.ctwing.entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Data {
    public WaterLevel waterLevel;
    public Map<String, Float> latesetData = new HashMap<>();
    public ConcurrentHashMap<String, Map<String, String>> cache = new ConcurrentHashMap();
}
