package com.example.wyy.dao;

import com.example.wyy.utils.EnterJdbcTemplate;
import com.example.wyy.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeleteDao extends EnterJdbcTemplate {
    public DeleteDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    //删除耗材的信息
    public String deleteById(Map map) {
        String infoList = Util.getStrOfObj(map.get("infoList"));
        String rtnStr = "";
        int num = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String dept_name = mapper.readTree(infoList).path("dept_name").asText();
            String stat_date = mapper.readTree(infoList).path("stat_date").asText();

            StringBuilder sql = new StringBuilder();
            sql.append("DELETE from  material_item_info where dept_name='"+dept_name+"' and stat_date='"+stat_date+"' \n");
            num= getJdbcTemplate().update(sql.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Integer.toString(num);
    }
    //删除医院的信息
    public String deleteByIdForHospital(Map map) {
        String infoList = Util.getStrOfObj(map.get("infoList"));
        String rtnStr = "";
        int num = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String dept_name = mapper.readTree(infoList).path("dept_name").asText();
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE from  dept_hospital where dept_name='"+dept_name+"' \n");
            num= getJdbcTemplate().update(sql.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Integer.toString(num);
    }
}
