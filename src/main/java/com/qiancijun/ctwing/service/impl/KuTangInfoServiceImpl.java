package com.qiancijun.ctwing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        return kuTangInfoDao.selectList(null);
    }

    @Override
    public Integer insertOne(KuTangInfo info) {
        int res = kuTangInfoDao.insert(info);
        return res;
    }

    @Override
    public Integer updateOne(KuTangInfo info) {
        return kuTangInfoDao.updateById(info);
    }

    @Override
    public Integer deleteOne(Integer id) {
        return kuTangInfoDao.deleteById(id);
    }

    @Override
    public List<KuTangInfo> getInfosByUserId(Integer id) {
        return kuTangInfoDao.selectList(new QueryWrapper<KuTangInfo>().eq("user_id", id));
    }


}
