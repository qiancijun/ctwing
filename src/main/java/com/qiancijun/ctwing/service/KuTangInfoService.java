package com.qiancijun.ctwing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiancijun.ctwing.entity.KuTangInfo;

import java.util.List;

public interface KuTangInfoService extends IService<KuTangInfo> {
    List<KuTangInfo> getAllInfo();
    Integer insertOne(KuTangInfo info);
    Integer updateOne(KuTangInfo info);
    Integer deleteOne(Integer id);
    List<KuTangInfo> getInfosByUserId(Integer id);
}
