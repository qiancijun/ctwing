package com.qiancijun.ctwing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiancijun.ctwing.dao.KuTangInfoDao;
import com.qiancijun.ctwing.entity.Data;
import com.qiancijun.ctwing.entity.KuTangInfo;
import com.qiancijun.ctwing.service.KuTangInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class KuTangInfoServiceImpl extends ServiceImpl<KuTangInfoDao, KuTangInfo> implements KuTangInfoService {

    @Autowired
    private KuTangInfoDao kuTangInfoDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Data data;

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

    @Override
    public Map<String, String> getDetails(Integer id) {
        // 有缓存
        ConcurrentHashMap<String, Map<String, String>> cache = data.cache;
        if (cache.containsKey(String.valueOf(id))) {
            log.info("缓存有");
            return cache.get(String.valueOf(id));
        }
        KuTangInfo kuTang = kuTangInfoDao.selectById(id);
        float latitude = kuTang.getLatitude();
        float longitude = kuTang.getLongitude();
        String url = "https://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + latitude + "," + longitude + "&key=13e3e70889e44e973fa858caaa32df36&radius=0&extensions=base";
        Map res = restTemplate.getForObject(url, Map.class);
        String city = (String) ((Map)((Map) res.get("regeocode")).get("addressComponent")).get("city");
        String province = (String) ((Map)((Map) res.get("regeocode")).get("addressComponent")).get("province");
        String district = (String) ((Map)((Map) res.get("regeocode")).get("addressComponent")).get("district");
        String formattedAddress = (String) (((Map) res.get("regeocode"))).get("formatted_address");
        log.info(city);
        log.info(province);
        log.info(district);
        log.info(formattedAddress);
        Map<String, String> details = new HashMap<>();
        details.put("city", city);
        details.put("province", province);
        details.put("district", district);
        details.put("formattedAddress", formattedAddress);
        // 添加缓存
        cache.put(String.valueOf(id), details);
        log.info(cache.toString());
        return details;
    }

    @Override
    public Integer kuTangCount() {
        return kuTangInfoDao.selectCount(null);
    }

    @Override
    public KuTangInfo getOneInfo(Integer id) {
        return kuTangInfoDao.selectOne(new QueryWrapper<KuTangInfo>().eq("id", id));
    }

    @Override
    public KuTangInfo getOneInfoByServiceId(String deviceId) {
        return kuTangInfoDao.selectOne(new QueryWrapper<KuTangInfo>().eq("deviceId", deviceId));
    }


}
