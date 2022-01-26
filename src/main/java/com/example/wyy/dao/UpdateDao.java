package com.example.wyy.dao;

import com.example.wyy.utils.EnterJdbcTemplate;
import com.example.wyy.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UpdateDao extends EnterJdbcTemplate {
    public UpdateDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    //更新表格基本信息
    public String updateDataForBasicInfo(Map map) {
        String infoList = Util.getStrOfObj(map.get("infoList"));
        String rtnStr = "";
        int num = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String dept_name = mapper.readTree(infoList).path("dept_name").asText();
            String scdj = mapper.readTree(infoList).path("scdj").asText();
            String zldj = mapper.readTree(infoList).path("zldj").asText();
            String A50x50 = mapper.readTree(infoList).path("A50x50").asText();
            String A70x120 = mapper.readTree(infoList).path("A70x120").asText();
            String A60x130 = mapper.readTree(infoList).path("A60x130").asText();
            //String A72x156 = mapper.readTree(infoList).path("A72x156").asText();
            String ydyl = mapper.readTree(infoList).path("ydyl").asText();
            String rhj = mapper.readTree(infoList).path("rhj").asText();
            String stat_date = mapper.readTree(infoList).path("stat_date").asText();
            String marke = mapper.readTree(infoList).path("marke").asText();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE material_item_info t  \n");
            sql.append("SET t.scdj = '" + scdj + "',            \n");
            sql.append(" t.zldj = '" + zldj + "',               \n");
            sql.append(" t.A50x50 = '" + A50x50 + "',             \n");
            sql.append(" t.A70x120 = '" + A70x120 + "',            \n");
            sql.append(" t.A60x130 = '" + A60x130 + "',            \n");
            //sql.append(" t.A72x156 = '" + A72x156 + "',            \n");
            sql.append(" t.ydyl = '" + ydyl + "',               \n");
            sql.append(" t.rhj = '" + rhj + "',                \n");
            sql.append(" t.marke = '" + marke + "'                \n");
            sql.append("WHERE                        \n");
            sql.append("	t.dept_name = '" + dept_name + "'        \n");
            sql.append("AND t.stat_date = '" + stat_date + "';       \n");
            num = getJdbcTemplate().update(sql.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Integer.toString(num);
    }

    //更新表格基本信息
    public String updateDataForHospital(Map map) {
        String infoList = Util.getStrOfObj(map.get("infoList"));
        String rtnStr = "";
        int num = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String dept_name = mapper.readTree(infoList).path("dept_name").asText();
            String dept_level = mapper.readTree(infoList).path("dept_level").asText();
            String distributor = mapper.readTree(infoList).path("distributor").asText();
            String city_name = mapper.readTree(infoList).path("city_name").asText();
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE dept_hospital \n");
            sql.append("SET dept_level = '" + dept_level + "',    \n");
            sql.append(" city_name = '" + city_name + "' ,      \n");
            sql.append(" distributor = '" + distributor + "'       \n");
            sql.append("WHERE  dept_name='" + dept_name + "' \n");
            num = getJdbcTemplate().update(sql.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Integer.toString(num);
    }
}
