package com.pm.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Double处理类
 * 
 * @author FYL_5728
 */

public class DoubleUtil {

    /**
     * FORMAT_1 格式 "#,##0.00"
     */
    public static final String FORMAT1 = "#,##0.00";

    /**
     * FORMAT_2 格式 "#0.00"
     */
    public static final String FORMAT2 = "#0.00";

    /**
     * FORMAT_3 格式 "#,##0.0000"
     */
    public static final String FORMAT3 = "#,##0.0000";

    /**
     * FORMAT_4 格式 "#0.0000"
     */
    public static final String FORMAT4 = "#0.0000";

    /**
     * FORMAT_5 格式 "###"
     */
    public static final String FORMAT5 = "###";

    /**
     * 默认格式化,返回2位小数
     * 
     * @param dlo 参数
     * @return 返回值
     */
    public static double getDouble(double dlo) {
        return getDouble(dlo, 2);
    }

    /**
     * 返回I位小数
     * 
     * @param dlo double
     * @param i 小数位数
     * @return 返回值
     */
    public static double getDouble(double dlo, int i) {
        BigDecimal bd = new BigDecimal(dlo);
        return bd.setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 指定格式化参数
     * 
     * @param dlo double
     * @param pattern 格式
     * @return String值
     */
    public static String formatString(double dlo, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(dlo);
    }

    /**
     * 把 String 转化为 double
     * 
     * @param s String格式
     * @return double格式
     */
    public static double getDouble(String s) {
        if (!StringUtil.isEmpty(s)) {
            s = s.replaceAll(" ", "").replaceAll(",", "").replaceAll("，", "");
            return Double.valueOf(s);
        }
        return 0;
    }
}
