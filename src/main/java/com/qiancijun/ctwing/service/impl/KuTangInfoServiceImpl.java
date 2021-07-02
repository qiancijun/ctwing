package com.qiancijun.ctwing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiancijun.ctwing.dao.KuTangInfoDao;
import com.qiancijun.ctwing.entity.KuTangInfo;
import com.qiancijun.ctwing.service.KuTangInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KuTangInfoServiceImpl extends ServiceImpl<KuTangInfoDao, KuTangInfo> implements KuTangInfoService {

    @Autowired
    private KuTangInfoDao kuTangInfoDao;

    public List<KuTangInfo> getAllInfo() {

    }
}
