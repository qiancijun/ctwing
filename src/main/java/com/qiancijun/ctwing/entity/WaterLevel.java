package com.qiancijun.ctwing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("water_level")
public class WaterLevel {
    @TableId
    private Long timestamp;
    /**
     * 数据上报的哪个Topic
     */
    private String serviceId;
    /**
     * 水位数据
     */
    private float level;
    /**
     * 设备编号
     */
    private String deviceId;
}
