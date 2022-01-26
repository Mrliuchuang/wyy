package com.example.wyy.utils;

public class Util {
    public static String getStrOfObj(Object parm) {
        return parm == null ? "" : parm.toString();
    }
    public static int getNumOfObj(Object parm) {
        if (parm == null || parm==""){
            return 0;
        }else{
            return Integer.parseInt(parm.toString());
        }
    }

    public static Boolean isBlank(String str) {
        return str == null || str.trim().equals("");
    }

    public static String getNum(String sql) {
        String start = "SELECT @rownum :=@rownum + 1 AS id, aa.* FROM (";
        String end = ") aa, (SELECT(@rowNum := 0)) bb";
        return start + sql + end;
    }
}
