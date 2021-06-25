package com.qiancijun.ctwing.entity;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Data {
    public WaterLevel waterLevel;
    public Map<String, Float> latesetData = new HashMap<>();
}
