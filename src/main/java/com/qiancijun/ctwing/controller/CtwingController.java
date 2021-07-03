package com.qiancijun.ctwing.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiancijun.ctwing.entity.*;
import com.qiancijun.ctwing.service.KuTangInfoService;
import com.qiancijun.ctwing.service.LevelService;
import com.qiancijun.ctwing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CtwingController {

    @Autowired
    private LevelService levelService;

    @Autowired
    private KuTangInfoService kuTangInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private Data data;

    @GetMapping("/getCurrentData/{deviceId}")
    public float getCurrentData(@PathVariable("deviceId") String id) {
        return data.latesetData.get(id) == null ? 0 : data.latesetData.get(id);
    }

    @GetMapping("/getData/{deviceId}")
    public List<WaterLevel> getDataByDeviceId(@PathVariable("deviceId") String id) {
        return levelService.getAllData(id);
    }

    @GetMapping("/getDevices")
    public List<String> getDeviceCount() {
        return levelService.getDevices();
    }

    @GetMapping("/getInfos")
    public R getInfos() {
        List<KuTangInfo> res = kuTangInfoService.getAllInfo();
        if (res == null) return new R(500, "查找失败，请查看错误日志");
        return new R(200, "查找成功", res);
    }

    @GetMapping("/getInfosById/{id}")
    public R getInfosById(@PathVariable("id") Integer id) {
        List<KuTangInfo> res = kuTangInfoService.getInfosByUserId(id);
        if (res == null) return new R(500, "查找失败，请查看错误日志");
        return new R(200, "查找成功", res);
    }

    @PostMapping("/insertInfo")
    public R<Boolean> insertInfo(@RequestBody KuTangInfo info) {
        Integer res = kuTangInfoService.insertOne(info);
        if (res == 1) {
            return new R<Boolean>(200, "插入成功", true);
        } else {
            return new R<Boolean>(500, "插入失败", false);
        }
    }

    @PostMapping("/updateInfo")
    public R<Boolean> updateInfo(@RequestBody KuTangInfo info) {
        Integer res = kuTangInfoService.updateOne(info);
        if (res == 0) {
            return new R<Boolean>(500, "修改失败", false);
        } else {
            return new R<Boolean>(200, "修改成功", true);
        }
    }

    @PostMapping("/deleteInfo")
    public R<Boolean> deleteInfo(@RequestBody Integer id) {
        Integer res = kuTangInfoService.deleteOne(id);
        if (res == 0) {
            return new R<Boolean>(500, "删除失败", false);
        } else {
            return new R<Boolean>(200, "删除成功", true);
        }
    }

    @PostMapping("/insertUser")
    public R<Boolean> insertUser(@RequestBody User user) {
        Integer res = userService.insertOne(user);
        if (res == 0) {
            return new R<Boolean>(500, "新增失败", false);
        } else {
            return new R<Boolean>(200, "新增成功", true);
        }
    }

    @PostMapping("/updateUser")
    public R<Boolean> updateUser(@RequestBody User user) {
        Integer res = userService.updateUser(user);
        if (res == 0) {
            return new R<Boolean>(500, "修改失败", false);
        } else {
            return new R<Boolean>(200, "修改成功", true);
        }
    }

    @PostMapping("/deleteUser")
    public R<Boolean> deleteUser(@RequestBody Integer id) {
        Integer res = userService.deleteUser(id);
        if (res == 0) {
            return new R<Boolean>(500, "删除失败", false);
        } else {
            return new R<Boolean>(200, "删除成功", true);
        }
    }

    @PostMapping("/login")
    public R login(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String pwd = data.get("password");
        User login = userService.login(email, pwd);
        if (login == null) {
            return new R(500, "登录失败，未知用户名或者密码错误");
        } else {
            return new R(200, "登录成功", login);
        }
    }

    @GetMapping("/userCount")
    public int userCount() {
        return userService.count();
    }

}
