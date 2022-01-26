package com.example.wyy.service.impl;

import com.example.wyy.controller.Insert;
import com.example.wyy.dao.DeleteDao;
import com.example.wyy.dao.InsertDao;
import com.example.wyy.service.DeleteService;
import com.example.wyy.service.InsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InsertImp implements InsertService {
    @Autowired
    InsertDao insertDao;

    @Override
    public String addInfo(Map map) {
        return insertDao.addInfo(map);
    }

    @Override
    public String addHospitalInfo(Map map) {
        return insertDao.addHospitalInfo(map);
    }
}
