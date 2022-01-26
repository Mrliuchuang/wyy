package com.example.wyy.bean;

import com.alibaba.excel.annotation.ExcelProperty;

public class getHospitalInfoByCityOrHospitalBean {
    @ExcelProperty("ID")
    String id;
    @ExcelProperty("医院名称")
    String dept_name;
    @ExcelProperty("所属城市")
    String city_name;
    @ExcelProperty("医院等级")
    String dept_level;
    @ExcelProperty("经销商公司")
    String distributor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDept_level() {
        return dept_level;
    }

    public void setDept_level(String dept_level) {
        this.dept_level = dept_level;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }
}
