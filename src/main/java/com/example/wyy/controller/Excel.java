package com.example.wyy.controller;

import com.alibaba.excel.EasyExcel;
import com.example.wyy.bean.getHospitalInfoByCityOrHospitalBean;
import com.example.wyy.bean.getInfoByBarBean;
import com.example.wyy.bean.getMaterialInfoByCityOrHospitalBean;
import com.example.wyy.service.GetService;
import com.example.wyy.utils.EnterJdbcTemplate;
import com.example.wyy.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excel")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Excel extends EnterJdbcTemplate {
    public Excel(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Autowired
    GetService getService;

    //耗材导出
    @RequestMapping("/getMaterialInfoByCityOrHospitalExcel")
    @ResponseBody
    public void getMaterialInfoByCityOrHospitalExcel(@RequestParam Map map, HttpServletResponse response) throws Exception {
        String city_name = Util.getStrOfObj(map.get("city_name"));
        String dept_name = Util.getStrOfObj(map.get("dept_name"));
        String stat_date = Util.getStrOfObj(map.get("stat_date"));
        Map<String, Object> rtnMap = new HashMap<>();
        String rtnStr = "";
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.*,b.city_name                                  \n");
        sql.append("FROM                                                          \n");
        sql.append("	material_item_info a                                      \n");
        sql.append("LEFT JOIN dept_hospital b ON a.dept_name = b.dept_name\n");
        sql.append("WHERE 1=1                                                     \n");
        if (!Util.isBlank(city_name)) {
            sql.append("AND b.city_name = '" + city_name + "'                           \n");
        }
        if (!Util.isBlank(dept_name)) {
            sql.append("AND a.dept_name like '%" + dept_name + "%'              \n");
        }
        if (!Util.isBlank(stat_date)) {
            sql.append("AND a.stat_date = '" + stat_date + "'              \n");
        }
        //sql.append("order by   a.stat_yea desc,    a.stat_month                    \n");
        List<getMaterialInfoByCityOrHospitalBean> list = getJdbcTemplate().query(Util.getNum(sql.toString()), new BeanPropertyRowMapper(getMaterialInfoByCityOrHospitalBean.class));
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "耗材信息";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), getMaterialInfoByCityOrHospitalBean.class).sheet("耗材").doWrite(list);
    }
    //医院导出
    @RequestMapping("/getHospitalInfoByCityOrHospitalExcel")
    @ResponseBody
    public void getHospitalInfoByCityOrHospitalExcel(@RequestParam Map map,HttpServletResponse response) throws Exception{
        String city_name = Util.getStrOfObj(map.get("city_name"));
        String dept_name = Util.getStrOfObj(map.get("dept_name"));
        Map<String, Object> rtnMap = new HashMap<>();
        String rtnStr = "";
        StringBuilder sql = new StringBuilder();
        sql.append("		SELECT      *            \n");
        sql.append("		FROM                    \n");
        sql.append("			dept_hospital  a     \n");
        sql.append("WHERE 1=1                                                     \n");
        if (!Util.isBlank(city_name)) {
            sql.append("AND a.city_name = '" + city_name + "'                           \n");
        }
        if (!Util.isBlank(dept_name)) {
            sql.append("AND a.dept_name like '%" + dept_name + "%'              \n");
        }
        //sql.append("order by   a.stat_yea desc,    a.stat_month                    \n");
        List<getHospitalInfoByCityOrHospitalBean> list = getJdbcTemplate().query(Util.getNum(sql.toString()), new BeanPropertyRowMapper(getHospitalInfoByCityOrHospitalBean.class));
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "医院信息";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), getHospitalInfoByCityOrHospitalBean.class).sheet("医院").doWrite(list);

    }
    //根据柱状图查表格Excel
    @RequestMapping("/getInfoByBarExcel")
    @ResponseBody
    public void getInfoByBarExcel(@RequestParam Map map,HttpServletResponse response) throws Exception{
        String rtnStr = "";
        String dept_name = Util.getStrOfObj(map.get("dept_name"));
        String statYear = Util.getStrOfObj(map.get("statYear"));
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT                                                                       \n");
        sql.append("	*                                                                        \n");
        sql.append("FROM                                                                         \n");
        sql.append("	material_item_info t                                                     \n");
        sql.append("WHERE                                                                        \n");
        sql.append("	STR_TO_DATE(t.stat_date, '%Y') = STR_TO_DATE('" + statYear + "', '%Y')\n");
        sql.append("AND t.dept_name = '" + dept_name + "'  order by stat_date asc             \n");
        //sql.append("order by   a.stat_yea desc,    a.stat_month                    \n");
        List<getInfoByBarBean> list = getJdbcTemplate().query(Util.getNum(sql.toString()), new BeanPropertyRowMapper(getInfoByBarBean.class));
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "医院信息";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), getInfoByBarBean.class).sheet("医院整年信息").doWrite(list);

    }
}
