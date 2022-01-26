package com.example.wyy.controller;

import com.example.wyy.service.DeleteService;
import com.example.wyy.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/delete")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Delete {
    @Autowired
    DeleteService deleteService;
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam Map map) {
        return deleteService.deleteById(map);
    }
    @RequestMapping("/deleteByIdForHospital")
    @ResponseBody
    public String deleteByIdForHospital(@RequestParam Map map) {
        return deleteService.deleteByIdForHospital(map);
    }

}
