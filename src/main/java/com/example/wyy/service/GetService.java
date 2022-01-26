package com.example.wyy.service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Map;

public interface GetService {
    String getHospitals();
    String getMaterialInfo();
    String getMaterialInfoByCityOrHospital(Map map);
    String getCity();
    String getHospitalInfo();
    String getHospitalInfoByCityOrHospital(Map map);
    String getDate( );
    String getInfoBar(Map map) throws ParseException;
    String getWarnInfo(Map map) throws ParseException;
    String getHospitalsByCity(Map map);
    String login(Map map);
    String loginInfo(Map map);
    String getInfoByBar(Map map);
}
