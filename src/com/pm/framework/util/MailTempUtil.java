package com.pm.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * 公用的配置文件处理类
 * 
 * @author FYL
 */

public class MailTempUtil {
    private static Logger LOGGER = Logger.getLogger(MailTempUtil.class);

    /**
     * 文件配置路径
     */
    public static final String PATHCONFIG = "/conf/mailtemp/";

    /**
     * 获得配置的值
     * 
     * @param name 文件名称
     * @return 值
     */
    public static String getValue(String name) {
        try {
            InputStream in = MailTempUtil.class.getResourceAsStream(PATHCONFIG + name);
            InputStreamReader read = new InputStreamReader(in, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            StringBuffer buffer = new StringBuffer();
            String lineTXT = null;
            while ((lineTXT = bufferedReader.readLine()) != null) {
                buffer.append(lineTXT.toString() + "\n");
            }
            read.close();
            return buffer.toString();
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }

    /**
     * 替换文件内容
     * 
     * @param msg 信息
     * @param name 名称
     * @param value 值
     * @return string
     */
    public static String fillParams(String msg, String name, String value) {
        try {
            return msg.replaceAll("#" + name + "#", value);
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }
}
