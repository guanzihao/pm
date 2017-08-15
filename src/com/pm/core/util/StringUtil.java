package com.pm.core.util;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 字符处理类
 * 
 * @author FYL
 */

public class StringUtil {

    public static final String COMMA = ",";
    public static final String POINT = ".";

    /**
     * 判断字符串
     * 
     * @param s 字符
     * @return true:为空or空字符
     */
    public static boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

    /**
     * 判断字符串是否存在
     * 
     * @param s1 原字符
     * @param s2 匹配字符
     * @return true：存在
     */
    public static boolean indexOf(String s1, String s2) {
        return s1.indexOf(s2) > -1;
    }

    /**
     * 判断两个字符是不是相同，不区分大小写
     * 
     * @param str1 字符1
     * @param str2 字符2
     * @return 相同返回true
     */
    public static boolean isEmpty(String str1, String str2) {
        if (!StringUtil.isEmpty(str1) && !StringUtil.isEmpty(str2)) {
            return str1.equalsIgnoreCase(str2);
        }
        return false;
    }

    /**
     * 把集合转化成字符串，以“,”隔开
     * 
     * @param params 数组
     * @return 字符串
     */
    public static String getListToString(List<String> params) {
        StringBuffer buffer = new StringBuffer();
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                buffer.append(params.get(i));
                if (i < params.size() - 1) {
                    buffer.append(COMMA);
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 把数组转化成字符串，以“,”隔开
     * 
     * @param params 数组
     * @return 字符串
     */
    public static String getArrayToString(String[] params) {
        StringBuffer buffer = new StringBuffer();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                buffer.append(params[i].toString());
                if (i < params.length - 1) {
                    buffer.append(COMMA);
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 拼接成SQL语句
     * 
     * @param id
     * @return 'id'
     */
    public static String getSqlId(String id) {
        return "'" + id + "'";
    }

    /**
     * 获得文件后缀
     * 
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (StringUtil.isEmpty(path)) {
            return "";
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return "";
    }

    /**
     * 得到随机标示
     * 
     * @return UUID 随机数36
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 随机获取制定位数的字符
     * 
     * @param len 位数
     * @return 字符
     */
    public static String getRandomStr(int len) {
        String s = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz";
        Random r = new Random();
        String result = "";
        for (int i = 0; i < len; i++) {
            int n = r.nextInt(s.length());
            result += s.substring(n, n + 1);
        }
        return result;
    }

    /**
     * 获得自定义颜色
     */
    public static String getFormatColor(int i) {
        String colors[] = new String[] { "#FF0000", "#0000FF", "#FF00FF", "#00FF00", "#FFFF00", "#000000", "#00FFFF" };
        return colors[i];
    }

    /**
     * 随机产生 颜色
     */
    public static String getColor() {
        Random rand = new Random();
        int red = rand.nextInt(256);
        int blue = rand.nextInt(256);
        int green = rand.nextInt(256);
        String col = "#";
        col += Integer.toHexString(red);
        col += Integer.toHexString(blue);
        col += Integer.toHexString(green);
        return col;
    }
    
    /** 
     * 获取名称后缀 
     * @param name 
     * @return 
     */  
    public static String getExt(String name){  
        if(name == null || "".equals(name) || !name.contains("."))  
            return "";  
        return name.substring(name.lastIndexOf(".")+1);  
    }  
}
