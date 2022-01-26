package com.example.wyy.service.impl;

import com.example.wyy.dao.DeleteDao;
import com.example.wyy.dao.GetDao;
import com.example.wyy.service.DeleteService;
import com.example.wyy.service.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeleteImp implements DeleteService {
    @Autowired
    DeleteDao deleteDao;

    @Override
    public String deleteById(Map map) {
        return deleteDao.deleteById(map);
    }

    @Override
    public String deleteByIdForHospital(Map map) {
        return deleteDao.deleteByIdForHospital(map);
    }
}
