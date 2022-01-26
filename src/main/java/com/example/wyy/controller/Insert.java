package com.example.wyy.controller;

import com.example.wyy.service.DeleteService;
import com.example.wyy.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/add")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Insert {
    @Autowired
    InsertService insertService;

    //添加耗材信息
    @RequestMapping("/addInfo")
    @ResponseBody
    public String addInfo(@RequestParam Map map) {
        return insertService.addInfo(map);
    }
    //添加医院信息
    @RequestMapping("/addHospitalInfo")
    @ResponseBody
    public String addHospitalInfo(@RequestParam Map map) {
        return insertService.addHospitalInfo(map);
    }
}
