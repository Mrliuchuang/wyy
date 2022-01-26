package com.example.wyy.controller;

import com.example.wyy.service.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("/get")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Get {
    @Autowired
    GetService getService;

    @RequestMapping("/getHospitals")
    @ResponseBody
    public String test() {
        return getService.getHospitals();
    }

    @RequestMapping("/getHospitalsByCity")
    @ResponseBody
    public String getHospitalsByCity(@RequestParam Map map) {
        return getService.getHospitalsByCity(map);
    }

    @RequestMapping("/getMaterialInfo")
    @ResponseBody
    public String getMaterialInfo() {
        return getService.getMaterialInfo();
    }

    @RequestMapping("/getMaterialInfoByCityOrHospital")
    @ResponseBody
    public String getMaterialInfoByCityOrHospital(@RequestParam Map map) {
        return getService.getMaterialInfoByCityOrHospital(map);
    }

    @RequestMapping("/getCity")
    @ResponseBody
    public String getCity() {
        return getService.getCity();
    }

    @RequestMapping("/getDate")
    @ResponseBody
    public String getDate() {
        return getService.getDate();
    }

    //-------------------------医院信息--------------------------------------
    //获取医院信息
    @RequestMapping("/getHospitalInfo")
    @ResponseBody
    public String getHospitalInfo() {
        return getService.getHospitalInfo();
    }

    //根据条件获取医院信息
    @RequestMapping("/getHospitalInfoByCityOrHospital")
    @ResponseBody
    public String getHospitalInfoByCityOrHospital(@RequestParam Map map) {
        return getService.getHospitalInfoByCityOrHospital(map);
    }

    //获取柱状图信息
    @RequestMapping("/getInfoBar")
    @ResponseBody
    public String getInfoBar(@RequestParam Map map) throws ParseException {
        return getService.getInfoBar(map);
    }

    //获取柱状图信息
    @RequestMapping("/getWarnInfo")
    @ResponseBody
    public String getWarnInfo(@RequestParam Map map) throws ParseException {
        return getService.getWarnInfo(map);
    }
    //获取登录信息
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam Map map) throws ParseException {
        return getService.login(map);
    }
    //获取登录信息
    @RequestMapping("/loginInfo")
    @ResponseBody
    public String loginInfo(@RequestParam Map map) throws ParseException {
        return getService.loginInfo(map);
    }
    //获取登录信息
    @RequestMapping("/getInfoByBar")
    @ResponseBody
    public String getInfoByBar(@RequestParam Map map) throws ParseException {
        return getService.getInfoByBar(map);
    }
}
