package com.example.wyy.controller;

import com.example.wyy.service.GetService;
import com.example.wyy.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/update")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Update {
    @Autowired
    UpdateService updateService;
    @RequestMapping("/updateData")
    @ResponseBody
    public String updateDataForBasicInfo(@RequestParam Map map) {
        return updateService.updateDataForBasicInfo(map);
    }

    @RequestMapping("/updateDataForHospital")
    @ResponseBody
    public String updateDataForHospital(@RequestParam Map map) {
        return updateService.updateDataForHospital(map);
    }

}
