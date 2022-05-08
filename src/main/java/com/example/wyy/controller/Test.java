package com.example.wyy.controller;

import com.alibaba.excel.EasyExcel;
import com.example.wyy.bean.getMaterialInfoByCityOrHospitalBean;
import com.example.wyy.utils.EnterJdbcTemplate;
import com.example.wyy.utils.Util;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class Test  extends EnterJdbcTemplate {
    public Test(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
    @RequestMapping("/test")
    @ResponseBody
    public  String test(){
        System.out.println("kkkkk");
        return "test";
    }

    public List<getMaterialInfoByCityOrHospitalBean> getMatInfo() {

        Map<String, Object> rtnMap = new HashMap<>();
        String rtnStr = "";
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.*,b.city_name                                  \n");
        sql.append("FROM                                                          \n");
        sql.append("	material_item_info a                                      \n");
        sql.append("LEFT JOIN dept_hospital b ON a.dept_name = b.dept_name\n");
        sql.append("WHERE 1=1                                                     \n");
        //sql.append("order by   a.stat_yea desc,    a.stat_month                    \n");
        List<getMaterialInfoByCityOrHospitalBean> list = getJdbcTemplate().query(Util.getNum(sql.toString()),new BeanPropertyRowMapper(getMaterialInfoByCityOrHospitalBean.class));
        return list;
    }
    @RequestMapping("/excel1")
    @ResponseBody
    public void getMaterialInfoByCityOrHospital(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "excel测试";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //单个sheet 浏览器下载
        //EasyExcel.write(response.getOutputStream(), UserEntity.class).sheet("模版1").doWrite(getData());
        EasyExcel.write(response.getOutputStream(), getMaterialInfoByCityOrHospitalBean.class).sheet("模版1").doWrite(getMatInfo());
        //EasyExcel.write(filename, MatBean.class).sheet("学生列表").doWrite(getMatInfo());
    }
    @RequestMapping("/exce22_newbranchasdsddddddddddddddddsd")
    @ResponseBody
    public void getMaterialInfoByCityOrHospital1(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "excel测试";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //单个sheet 浏览器下载
        //EasyExcel.write(response.getOutputStream(), UserEntity.class).sheet("模版1").doWrite(getData());
        EasyExcel.write(response.getOutputStream(), getMaterialInfoByCityOrHospitalBean.class).sheet("模版1").doWrite(getMatInfo());
        //EasyExcel.write(filename, MatBean.class).sheet("学生列表").doWrite(getMatInfo());
    }
}