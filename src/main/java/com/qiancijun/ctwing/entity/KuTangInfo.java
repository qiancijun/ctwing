package com.qiancijun.ctwing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KuTangInfo {
    private Integer id; // 库唐编号
    private String name; // 库唐名称
    private float latitude; // 经度
    private float longitude; // 维度
    private String deviceId; // 所绑定的设备编号
    private float threshold; // 阈值
    private Integer maxDepth; // 最大深度
    private Integer userId; // 所属用户Id
}
