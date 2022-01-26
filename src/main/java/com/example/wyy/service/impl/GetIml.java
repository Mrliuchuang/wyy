package com.example.wyy.service.impl;

import com.example.wyy.dao.GetDao;
import com.example.wyy.service.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;

@Service
public class GetIml implements GetService {
    @Autowired
    GetDao getDao;
    @Override
    public String getHospitals() {
        return getDao.getHospitals();
    }

    @Override
    public String getMaterialInfo() {
        return getDao.getMaterialInfo();
    }

    @Override
    public String getMaterialInfoByCityOrHospital(Map map) {
        return getDao.getMaterialInfoByCityOrHospital(map);
    }

    @Override
    public String getCity() {
        return getDao.getCity();
    }

    @Override
    public String getHospitalInfo() {
        return getDao.getHospitalInfo();
    }

    @Override
    public String getHospitalInfoByCityOrHospital(Map map) {
        return getDao.getHospitalInfoByCityOrHospital(map);
    }

    @Override
    public String getDate() {
        return getDao.getDate();
    }

    @Override
    public String getInfoBar(Map map) throws ParseException {
        return getDao.getInfoBar(map);
    }

    @Override
    public String getWarnInfo(Map map) throws ParseException {
        return getDao.getWarnInfo(map);
    }

    @Override
    public String getHospitalsByCity(Map map) {
        return getDao.getHospitalsByCity(map);
    }

    @Override
    public String login(Map map) {
        return getDao.login(map);
    }

    @Override
    public String loginInfo(Map map) {
        return getDao.loginInfo(map);
    }
    @Override
    public String getInfoByBar(Map map) {
        return getDao.getInfoByBar(map);
    }
}
