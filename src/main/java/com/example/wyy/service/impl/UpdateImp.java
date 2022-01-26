package com.example.wyy.service.impl;

import com.example.wyy.dao.GetDao;
import com.example.wyy.dao.UpdateDao;
import com.example.wyy.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateImp implements UpdateService {
    @Autowired
    UpdateDao updateDao;

    @Override
    public String updateDataForBasicInfo(Map map) {
        return updateDao.updateDataForBasicInfo(map);
    }

    @Override
    public String updateDataForHospital(Map map) {
        return updateDao.updateDataForHospital(map);
    }
}
