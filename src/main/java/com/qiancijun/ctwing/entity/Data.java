package com.qiancijun.ctwing.entity;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Data {
    public WaterLevel waterLevel;
    public Map<String, Float> latesetData = new HashMap<>();
    public ConcurrentHashMap<String, Map<String, String>> cache = new ConcurrentHashMap();
    public ConcurrentHashMap<String, KuTangInfo> kuTangcache = new ConcurrentHashMap();
    public Queue<String> q = new LinkedList<>();
    public Set<String> set = new HashSet<>();
}
