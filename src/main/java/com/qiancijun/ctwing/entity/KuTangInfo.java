package com.qiancijun.ctwing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KuTangInfo {
    private int id;
    private String name;
    private float latitude;
    private float longitude;
    private String deviceId;
}
