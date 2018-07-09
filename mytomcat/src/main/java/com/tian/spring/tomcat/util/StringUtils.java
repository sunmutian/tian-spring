package com.tian.spring.tomcat.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串操作哦工具类
 *
 * @author tianweichang
 * @date 2018-07-09 13:59
 **/
public class StringUtils {

    public static String[] split(String str, String regex) {
        return str.split(regex);
    }

    /**
     * 解析
     * userName=zhangsan&userId=100001
     *
     */
    public static Map<String, Object> convertParameters(String s) {
        Map<String, Object> result = new HashMap<>();
        if (s == null || s.trim() == "") {
            return null;
        }
        if (!s.contains("&") && s.contains("=")) {
            String[] ps = StringUtils.split(s, "=");
            result.put(ps[0], ps[1]);
            return result;
        }
        String[] strings = StringUtils.split(s, "&");
        for (String string : strings) {
            String[] ps = StringUtils.split(string, "=");
            result.put(ps[0], ps[1]);
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "userName=zhangsan";
        String[] strs = split(str, "=");
        for (String s : strs) {
            System.out.println(s);
        }
    }
}
