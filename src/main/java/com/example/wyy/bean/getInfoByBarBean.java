package com.example.wyy.bean;

import com.alibaba.excel.annotation.ExcelProperty;

public class getInfoByBarBean {
    @ExcelProperty("ID")
    String id;
    @ExcelProperty("单位名称")
    String dept_name;
    @ExcelProperty("50x50")
    String a50x50;
    @ExcelProperty("60x130")
    String a60x130;
    @ExcelProperty("70x120")
    String a70x120;
    @ExcelProperty("润滑剂")
    String rhj;
    @ExcelProperty("筛查电极")
    String scdj;
    @ExcelProperty("阴道哑铃")
    String ydyl;
    @ExcelProperty("治疗电极")
    String zldj;
    @ExcelProperty("统计日期")
    String stat_date;
    @ExcelProperty("备注")
    String marke;

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

    public String getA50x50() {
        return a50x50;
    }

    public void setA50x50(String a50x50) {
        this.a50x50 = a50x50;
    }

    public String getA60x130() {
        return a60x130;
    }

    public void setA60x130(String a60x130) {
        this.a60x130 = a60x130;
    }

    public String getA70x120() {
        return a70x120;
    }

    public void setA70x120(String a70x120) {
        this.a70x120 = a70x120;
    }

    public String getRhj() {
        return rhj;
    }

    public void setRhj(String rhj) {
        this.rhj = rhj;
    }

    public String getScdj() {
        return scdj;
    }

    public void setScdj(String scdj) {
        this.scdj = scdj;
    }

    public String getYdyl() {
        return ydyl;
    }

    public void setYdyl(String ydyl) {
        this.ydyl = ydyl;
    }

    public String getZldj() {
        return zldj;
    }

    public void setZldj(String zldj) {
        this.zldj = zldj;
    }

    public String getStat_date() {
        return stat_date;
    }

    public void setStat_date(String stat_date) {
        this.stat_date = stat_date;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }
}
