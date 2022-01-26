package com.example.wyy.dao;

import com.example.wyy.utils.EnterJdbcTemplate;
import com.example.wyy.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class InsertDao extends EnterJdbcTemplate {
    public InsertDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    //添加耗材的信息
    public String addInfo(Map map) {
        String infoList = Util.getStrOfObj(map.get("infoList"));
        int num = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String dept_name = mapper.readTree(infoList).path("adddept_name").asText();
            String scdj = mapper.readTree(infoList).path("addscdj").asText();
            String zldj = mapper.readTree(infoList).path("addzldj").asText();
            String A50x50 = mapper.readTree(infoList).path("addA50x50").asText();
            String A70x120 = mapper.readTree(infoList).path("addA70x120").asText();
            String A60x130 = mapper.readTree(infoList).path("addA60x130").asText();
            //String A72x156 = mapper.readTree(infoList).path("addA72x156").asText();
            String ydyl = mapper.readTree(infoList).path("addydyl").asText();
            String rhj = mapper.readTree(infoList).path("addrhj").asText();
            String stat_date = mapper.readTree(infoList).path("addstat_date").asText();
            String marke = mapper.readTree(infoList).path("addmarke").asText();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO material_item_info  ( \n");
            sql.append("	dept_name,                   \n");
            sql.append("	stat_date,                   \n");
            sql.append("	scdj,                        \n");
            sql.append("	zldj,                        \n");
            sql.append("	A50x50,                      \n");
            sql.append("	A70x120,                     \n");
            sql.append("	A60x130,                     \n");
            //sql.append("	A72x156,                     \n");
            sql.append("	ydyl,                        \n");
            sql.append("	rhj,                         \n");
            sql.append("	marke                        \n");
            sql.append(")                                  \n");
            sql.append("VALUES                             \n");
            sql.append("	(                              \n");
            sql.append("		'"+dept_name+"',                      \n");
            sql.append("		'"+stat_date+"',                      \n");
            sql.append("		'"+scdj+"',                      \n");
            sql.append("		'"+zldj+"',                      \n");
            sql.append("		'"+A50x50+"',                      \n");
            sql.append("		'"+A70x120+"',                      \n");
            sql.append("		'"+A60x130+"',                      \n");
            //sql.append("		'"+A72x156+"',                      \n");
            sql.append("		'"+ydyl+"',                      \n");
            sql.append("		'"+rhj+"',                      \n");
            sql.append("		'"+marke+"'                       \n");
            sql.append("	)                              \n");
            getJdbcTemplate().execute(sql.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Integer.toString(num);
    }

    //添加医院的信息
    public String addHospitalInfo(Map map) {
        String infoList = Util.getStrOfObj(map.get("infoList"));
        int num = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String adddept_name = mapper.readTree(infoList).path("adddept_name").asText();
            String adddept_level = mapper.readTree(infoList).path("adddept_level").asText();
            String adddistributor = mapper.readTree(infoList).path("adddistributor").asText();
            String addcity_name = mapper.readTree(infoList).path("addcity_name").asText();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO dept_hospital (   \n");
            sql.append("	dept_name,                 \n");
            sql.append("	dept_level,                 \n");
            sql.append("	city_name ,                     \n");
            sql.append("	distributor                      \n");
            sql.append(")                                  \n");
            sql.append("VALUES                             \n");
            sql.append("	('" + adddept_name + "', '" + adddept_level + "', '" + addcity_name + "', '" + adddistributor + "') \n");
            num = getJdbcTemplate().update(sql.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Integer.toString(num);
    }
}
