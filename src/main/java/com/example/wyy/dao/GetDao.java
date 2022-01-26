package com.example.wyy.dao;

import com.alibaba.excel.EasyExcel;
import com.example.wyy.bean.getMaterialInfoByCityOrHospitalBean;
import com.example.wyy.utils.EnterJdbcTemplate;
import com.example.wyy.utils.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class GetDao extends EnterJdbcTemplate {
    public GetDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    //获取医院的信息
    public String getHospitals() {
        String rtnStr = "";
        String getHospitalSql = "select t.dept_name value  from dept_hospital t group by dept_name";
        List list = getJdbcTemplate().queryForList(getHospitalSql);
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //获取医院的信息
    public String getHospitalsByCity(Map map) {
        String cityName = Util.getStrOfObj(map.get("city_name"));
        String rtnStr = "";
        StringBuilder getHospitalSql = new StringBuilder();
        getHospitalSql.append("select t.dept_name value  from dept_hospital t where 1=1 ");
        if (!cityName.equals("") || cityName != null) {
            getHospitalSql.append(" and t.city_name='" + cityName + "' ");
        }
        getHospitalSql.append("  group by t.dept_name");
        List list = getJdbcTemplate().queryForList(getHospitalSql.toString());
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //获取城市的信息
    public String getCity() {
        List<Map<String, Object>> list = new ArrayList<>();
        String rtnStr = "";
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT                                                         \n");
        sql.append("	b.city_name value                                           \n");
        sql.append("FROM                                                           \n");
        sql.append("dept_hospital b \n");
        sql.append("GROUP BY                                                       \n");
        sql.append("	b.city_name                                                \n");
        list = getJdbcTemplate().queryForList(sql.toString());
        Map<String, Object> temMap = new HashMap<>();
        temMap.put("value", "");
        list.add(temMap);
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //获取日期的信息
    public String getDate() {
        List<Map<String, Object>> list = new ArrayList<>();
        String rtnStr = "";
        StringBuilder sqlDate = new StringBuilder();
        sqlDate.append("SELECT                                                         \n");
        sqlDate.append("	b.stat_date value                                           \n");
        sqlDate.append("FROM                                                           \n");
        sqlDate.append("material_item_info b \n");
        sqlDate.append("GROUP BY                                                       \n");
        sqlDate.append("	b.stat_date                                                \n");
        list = getJdbcTemplate().queryForList(sqlDate.toString());
        Map<String, Object> temMap = new HashMap<>();
        temMap.put("value", "");
        list.add(temMap);
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //获取耗材使用的信息
    public String getMaterialInfo() {
        Map<String, Object> rtnMap = new HashMap<>();
        String rtnStr = "";
        String sql = "SELECT a.*,b.city_name from material_item_info a left join dept_hospital b on a.dept_name=b.dept_name order by a.stat_date desc";
        String sqlCount = "SELECT count(*) num from material_item_info a";
        List list = getJdbcTemplate().queryForList(Util.getNum(sql));
        List<Map<String, Object>> list1 = getJdbcTemplate().queryForList(sqlCount);
        rtnMap.put("list", list);
        rtnMap.put("pageTotal", list1.get(0).get("num"));
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(rtnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //按城市按医院获取耗材信息
    public String getMaterialInfoByCityOrHospital(Map map) {
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
        List list = getJdbcTemplate().queryForList(Util.getNum(sql.toString()));
        String countsql = "select count(1) count_num from (" + sql + ") t";
        List<Map<String, Object>> countList = getJdbcTemplate().queryForList(countsql.toString());
        rtnMap.put("list", list);
        rtnMap.put("pageTotal", countList.get(0).get("count_num"));
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(rtnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }
    //获取医院信息
    public String getHospitalInfo() {
        Map<String, Object> rtnMap = new HashMap<>();
        String rtnStr = "";
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT                          \n");
        sql.append("	@rownum :=@rownum + 1 AS id,\n");
        sql.append("	a.*                         \n");
        sql.append("FROM                            \n");
        sql.append("	(                           \n");
        sql.append("		SELECT  *                \n");
        sql.append("		FROM                    \n");
        sql.append("			dept_hospital       \n");
        sql.append("	) a,                        \n");
        sql.append("	(SELECT(@rowNum := 0)) b    \n");
        List list = getJdbcTemplate().queryForList(sql.toString());
        String countsql = "select count(1) count_num from (" + sql + ") t";
        List<Map<String, Object>> countList = getJdbcTemplate().queryForList(countsql.toString());
        rtnMap.put("list", list);
        rtnMap.put("pageTotal", countList.get(0).get("count_num"));
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(rtnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //按城市按医院获取医院信息
    public String getHospitalInfoByCityOrHospital(Map map) {
        String city_name = Util.getStrOfObj(map.get("city_name"));
        String dept_name = Util.getStrOfObj(map.get("dept_name"));
        Map<String, Object> rtnMap = new HashMap<>();
        String rtnStr = "";
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT                          \n");
        sql.append("	@rownum :=@rownum + 1 AS id,\n");
        sql.append("	a.*                         \n");
        sql.append("FROM                            \n");
        sql.append("	(                           \n");
        sql.append("		SELECT      *            \n");
        sql.append("		FROM                    \n");
        sql.append("			dept_hospital       \n");
        sql.append("	) a,                        \n");
        sql.append("	(SELECT(@rowNum := 0)) b    \n");
        sql.append("WHERE 1=1                                                     \n");
        if (!Util.isBlank(city_name)) {
            sql.append("AND a.city_name = '" + city_name + "'                           \n");
        }
        if (!Util.isBlank(dept_name)) {
            sql.append("AND a.dept_name like '%" + dept_name + "%'              \n");
        }
        //sql.append("order by   a.stat_yea desc,    a.stat_month                    \n");
        List list = getJdbcTemplate().queryForList(sql.toString());
        String countsql = "select count(1) count_num from (" + sql + ") t";
        List<Map<String, Object>> countList = getJdbcTemplate().queryForList(countsql.toString());
        rtnMap.put("list", list);
        rtnMap.put("pageTotal", countList.get(0).get("count_num"));
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(rtnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }
    //获取耗材情况柱状图
    public String getInfoBar(Map map) throws ParseException {
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
        sql.append("AND t.dept_name = '" + dept_name + "'                                           \n");
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql.toString());
        Map mapRtn = new HashMap<>();
        List<Integer> scdj = new ArrayList<>();
        List<Integer> zldj = new ArrayList<>();
        List<Integer> A50x50 = new ArrayList<>();
        List<Integer> A70x120 = new ArrayList<>();
        List<Integer> A60x130 = new ArrayList<>();
       // List<Integer> A72x156 = new ArrayList<>();
        //List<Integer> ydyl = new ArrayList<>();
        List<Integer> rhj = new ArrayList<>();
        List<String> stat_date = new ArrayList<>();

        Map<String, Object> labelMap1 = new HashMap();
        Map<String, Object> labelMap2 = new HashMap();
        Map<String, Object> labelMap3 = new HashMap();
        Map<String, Object> labelMap4 = new HashMap();
        Map<String, Object> labelMap5 = new HashMap();
       // Map<String, Object> labelMap6 = new HashMap();
        //Map<String, Object> labelMap7 = new HashMap();
        Map<String, Object> labelMap8 = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (int i = 1; i <= 12; i++) {
            for (Map map1 : list) {
                String stat_dateTem = Util.getStrOfObj(map1.get("stat_date"));
                if (sdf.parse(stat_dateTem).getTime() == sdf.parse(statYear + "-" + i).getTime()) {
                    scdj.add(Util.getNumOfObj(map1.get("scdj")));
                    zldj.add(Util.getNumOfObj(map1.get("zldj")));
                    A50x50.add(Util.getNumOfObj(map1.get("A50x50")));
                    A70x120.add(Util.getNumOfObj(map1.get("A70x120")));
                    A60x130.add(Util.getNumOfObj(map1.get("A60x130")));
                   // A72x156.add(Util.getNumOfObj(map1.get("A72x156")));
                   // ydyl.add(Util.getNumOfObj(map1.get("ydyl")));
                    rhj.add(Util.getNumOfObj(map1.get("rhj")));
                    // stat_date.add(Util.getStrOfObj(map1.get("stat_date")));
                    stat_date.add(i + "月");
                }
            }
            if (scdj.size() == i - 1) {
                scdj.add(0);
                zldj.add(0);
                A50x50.add(0);
                A70x120.add(0);
                A60x130.add(0);
                //A72x156.add(0);
               // ydyl.add(0);
                rhj.add(0);
                stat_date.add(i + "月");
            }

        }
        labelMap1.put("label", "筛查电极");
        labelMap1.put("data", scdj);
        labelMap2.put("label", "治疗电极");
        labelMap2.put("data", zldj);
        labelMap3.put("label", "50*50");
        labelMap3.put("data", A50x50);
        labelMap4.put("label", "70*120");
        labelMap4.put("data", A70x120);
        labelMap4.put("fillColor", "#FF00FF");
        labelMap5.put("label", "60*130");
        labelMap5.put("data", A60x130);
        //labelMap6.put("label", "72*156");
       // labelMap6.put("data", A72x156);
        //labelMap7.put("label", "阴道哑铃");
        //labelMap7.put("data", ydyl);
        labelMap8.put("label", "润滑剂");
        labelMap8.put("data", rhj);
        List<Map> listTem = new ArrayList<>();
        listTem.add(labelMap1);
        listTem.add(labelMap2);
        listTem.add(labelMap3);
        listTem.add(labelMap4);
        listTem.add(labelMap5);
       // listTem.add(labelMap6);
       // listTem.add(labelMap7);
        listTem.add(labelMap8);
        Map<String, Object> rtnMap = new HashMap();
        rtnMap.put("labels", stat_date);
        rtnMap.put("bardata", listTem);

        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(rtnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //获取耗材情况柱状图
    public String getInfoBarCopy(Map map) {
        String rtnStr = "";
        String dept_name = Util.getStrOfObj(map.get("dept_name"));
        String startTime = Util.getStrOfObj(map.get("startTime"));
        String endTime = Util.getStrOfObj(map.get("endTime"));
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT                                                                       \n");
        sql.append("	*                                                                        \n");
        sql.append("FROM                                                                         \n");
        sql.append("	material_item_info t                                                     \n");
        sql.append("WHERE                                                                        \n");
        sql.append("	STR_TO_DATE(t.stat_date, '%Y-%m') BETWEEN STR_TO_DATE('" + startTime + "', '%Y-%m')\n");
        sql.append("AND STR_TO_DATE('" + endTime + "', '%Y-%m')                                          \n");
        sql.append("AND t.dept_name = '" + dept_name + "'                                           \n");
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql.toString());
        Map<String, List<String>> mapRtn = new HashMap<>();
        List<String> scdj = new ArrayList<>();
        List<String> zldj = new ArrayList<>();
        List<String> A50x50 = new ArrayList<>();
        List<String> A70x120 = new ArrayList<>();
        List<String> A60x130 = new ArrayList<>();
        List<String> A72x156 = new ArrayList<>();
        List<String> ydyl = new ArrayList<>();
        List<String> rhj = new ArrayList<>();
        List<String> stat_date = new ArrayList<>();

        Map<String, Object> labelMap1 = new HashMap();
        Map<String, Object> labelMap2 = new HashMap();
        Map<String, Object> labelMap3 = new HashMap();
        Map<String, Object> labelMap4 = new HashMap();
        Map<String, Object> labelMap5 = new HashMap();
        Map<String, Object> labelMap6 = new HashMap();
        Map<String, Object> labelMap7 = new HashMap();
        Map<String, Object> labelMap8 = new HashMap();
        for (Map map1 : list) {
            scdj.add(Util.getStrOfObj(map1.get("scdj")));
            zldj.add(Util.getStrOfObj(map1.get("zldj")));
            A50x50.add(Util.getStrOfObj(map1.get("A50x50")));
            A70x120.add(Util.getStrOfObj(map1.get("A70x120")));
            A60x130.add(Util.getStrOfObj(map1.get("A60x130")));
            A72x156.add(Util.getStrOfObj(map1.get("A72x156")));
            ydyl.add(Util.getStrOfObj(map1.get("ydyl")));
            rhj.add(Util.getStrOfObj(map1.get("rhj")));
            stat_date.add(Util.getStrOfObj(map1.get("stat_date")));
        }
        labelMap1.put("label", "筛查电极");
        labelMap1.put("data", scdj);
        labelMap2.put("label", "治疗电极");
        labelMap2.put("data", zldj);
        labelMap3.put("label", "50*50");
        labelMap3.put("data", A50x50);
        labelMap4.put("label", "70*120");
        labelMap4.put("data", A70x120);
        labelMap5.put("label", "60*130");
        labelMap5.put("data", A60x130);
        labelMap6.put("label", "72*156");
        labelMap6.put("data", A72x156);
        labelMap7.put("label", "阴道哑铃");
        labelMap7.put("data", ydyl);
        labelMap8.put("label", "润滑剂");
        labelMap8.put("data", rhj);
        List<Map> listTem = new ArrayList<>();
        listTem.add(labelMap1);
        listTem.add(labelMap2);
        listTem.add(labelMap3);
        listTem.add(labelMap4);
        listTem.add(labelMap5);
        listTem.add(labelMap6);
        listTem.add(labelMap7);
        listTem.add(labelMap8);
        Map<String, Object> rtnMap = new HashMap();
        rtnMap.put("labels", stat_date);
        rtnMap.put("bardata", listTem);

        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(rtnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //获取预警信息
    public String getWarnInfoCopy(Map map) throws ParseException {
        String rtnStr = "";
        String dept_name = Util.getStrOfObj(map.get("dept_name"));
        String startTime = Util.getStrOfObj(map.get("startTime"));
        String endTime = Util.getStrOfObj(map.get("endTime"));
        StringBuilder sqlDept = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String temDate = startTime;
        Date dt = null;
        int flag = 0;
        List<Map<String, Object>> rtnList = new ArrayList();
        Map rtnMap = null;
        while (sdf.parse(temDate).before(sdf.parse(endTime))) {
            try {
                dt = sdf.parse(temDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //新增三个月
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, 2);
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);

            System.out.println(reStr);
            sqlDept.append("SELECT   * from dept_hospital                                                   \n");
            List<Map<String, Object>> listDept = getJdbcTemplate().queryForList(sqlDept.toString());
            Set setDeptAll = new HashSet();
            for (Map map1 : listDept) {
                setDeptAll.add(map1.get("dept_name"));
            }
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT  *  FROM  material_item_info t  WHERE  \n");
            sql.append("STR_TO_DATE(t.stat_date, '%Y-%m') between STR_TO_DATE('" + temDate + "', '%Y-%m') AND STR_TO_DATE('" + reStr + "', '%Y-%m') \n");
            List<Map<String, Object>> listT = getJdbcTemplate().queryForList(sql.toString());
            sql.delete(0, sql.length());
            sqlDept.delete(0, sqlDept.length());
            Set setDeptTem = new HashSet();
            for (Map map1 : listT) {
                setDeptTem.add(map1.get("dept_name"));
            }
            setDeptAll.removeAll(setDeptTem);
            for (Object str : setDeptAll) {
                rtnMap = new HashMap();
                rtnMap.put("dept_name", str);
                rtnMap.put("stat_date", temDate + "到" + reStr);
                rtnList.add(rtnMap);
            }
            Date dt2 = sdf.parse(temDate);
            Calendar rightNow2 = Calendar.getInstance();
            rightNow.setTime(dt2);
            rightNow.add(Calendar.MONTH, 1);
            Date dt3 = rightNow.getTime();
            String reStr2 = sdf.format(dt3);
            temDate = reStr2;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            //获取预警的医院个数   去重
            HashMap warnNum = new HashMap();
            int startNum = 1;
            for (Map map1 : rtnList) {
                warnNum.put(map1.get("dept_name"), "1");
                map1.put("id", startNum);
                startNum++;
            }
            Map rtnMap1 = new HashMap();
            rtnMap1.put("count", warnNum.size());
            rtnMap1.put("list", rtnList);
            rtnMap1.put("pageCount", rtnList.size());
            rtnStr = mapper.writeValueAsString(rtnMap1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }

    //获取预警信息
    public String getWarnInfo(Map map) {
        String rtnStr = "";
        String dept_name = Util.getStrOfObj(map.get("dept_name"));
        String startTime = Util.getStrOfObj(map.get("startTime"));
        String endTime = Util.getStrOfObj(map.get("endTime"));
        StringBuilder sqlDept = new StringBuilder();
        sqlDept.append("SELECT  dept_name ,city_name,dept_level  from dept_hospital             \n");
        List<Map<String, Object>> listDept = getJdbcTemplate().queryForList(sqlDept.toString());
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  t.dept_name,p.city_name,p.dept_level  FROM  material_item_info t left join dept_hospital p  on t.dept_name=p.dept_name WHERE  \n");
        sql.append("STR_TO_DATE(t.stat_date, '%Y-%m') between STR_TO_DATE('" + startTime + "', '%Y-%m') AND STR_TO_DATE('" + endTime + "', '%Y-%m') \n");

        List<Map<String, Object>> listT = getJdbcTemplate().queryForList(sql.toString());

        listDept.removeAll(listT);
        Map rtnMap = new HashMap();
        int startNum = 1;
        for (Map map1 : listDept) {
            map1.put("id", startNum);
            startNum++;
            rtnMap.put("count", listDept.size());
            rtnMap.put("list", listDept);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(rtnMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }
    //获取登录信息
    public String login(Map map) {
        String rtnStr = "";
        String username = Util.getStrOfObj(map.get("username"));
        String password = Util.getStrOfObj(map.get("password"));
        String loginIp = Util.getStrOfObj(map.get("loginIp"));
        String loginAdress = Util.getStrOfObj(map.get("loginAdress"));
        StringBuilder sql= new StringBuilder();
        sql.append("SELECT  *  from user_sys where  username='"+username+"' and  password='"+password+"' \n");
        List<Map<String, Object>> list= getJdbcTemplate().queryForList(sql.toString());
        if (list.size()>0){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            StringBuilder sqlInsert= new StringBuilder();
            sqlInsert.append("insert into  user_log (login_ip,login_adress,login_time,username) values('"+loginIp+"','"+loginAdress+"','"+df.format(new Date())+"','"+username+"') \n");
            getJdbcTemplate().update(sqlInsert.toString());
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }
    //获取登录信息
    public String loginInfo(Map map) {
        String rtnStr = "";
        String username = Util.getStrOfObj(map.get("username"));
        StringBuilder sql= new StringBuilder();
        sql.append("SELECT  *  from user_log where  username='"+username+"' order by id desc  \n");
        List<Map<String, Object>> list= getJdbcTemplate().queryForList(sql.toString());
        StringBuilder sqlCount= new StringBuilder();
        sqlCount.append("SELECT  count(*) count  from user_log where  username='"+username+"'   \n");
        List<Map<String, Object>> listCount= getJdbcTemplate().queryForList(sqlCount.toString());
        list.get(1).put("count",listCount.get(0).get("count"));
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(list.get(1));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }
    //获取登录信息
    public String getInfoByBar(Map map) {
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
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(Util.getNum(sql.toString()));
        ObjectMapper mapper = new ObjectMapper();
        try {
            rtnStr = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rtnStr;
    }
}
