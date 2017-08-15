package com.pm.framework.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.pm.core.util.StringUtil;

/**
 * IP工具类
 * 
 * @author youliang.fang
 */
@SuppressWarnings("unchecked")
public class IpUtil {
    public final static String SAFEIP = "safe.ip";
    public final static String LOCALHOST = "172.0.0.1";

    /**
     * 获得客户端UserBrowse
     * 
     * @param request HttpServletRequest
     * @return System
     */
    public static String getUserBrowse(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.indexOf("Windows") > -1)
            return "Windows";
        if (userAgent.indexOf("Linux") > -1)
            return "Linux";
        if (userAgent.indexOf("iPhone") > -1)
            return "iPhone";
        if (userAgent.indexOf("iPad") > -1)
            return "iPad";
        if (userAgent.indexOf("iPod") > -1)
            return "iPod";
        if (userAgent.indexOf("Mac") > -1)
            return "Mac";
        if (userAgent.indexOf("Android") > -1)
            return "Android";
        return "";
    }

    /**
     * 获得IP地址
     * 
     * @param request HttpServletRequest
     * @return IP
     */
    public static String getIpInfo(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (StringUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!StringUtil.isEmpty(ip) && ip.equals("0:0:0:0:0:0:0:1")) {
            ip = LOCALHOST;
        }
        return ip;
    }

    /**
     * 检查是否本地IP
     * 
     * @param ipInfo
     * @return
     */
    public static boolean chechLocal(String ipInfo) {
        return LOCALHOST.equals(ipInfo);
    }

    /**
     * 检查是否安全IP
     * 
     * @param ipInfo
     * @return
     */
    public static boolean chechSafeIp(String ipInfo) {
        String ips = GlobalUtil.getValue(SAFEIP);
        if (!StringUtil.isEmpty(ips)) {
            String[] sip = ips.split(",");
            for (String ip : sip) {
                if (ipInfo.equals(ip)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取request所有参数
     * 
     * @param request request
     * @return string
     */
    public static String getRequestParameters(HttpServletRequest request) {
        String urlString = request.getRequestURI();
        Enumeration<String> enu = request.getParameterNames();
        if (enu.hasMoreElements()) {
            urlString += "?";
        }

        int methodSize = 0;
        while (enu.hasMoreElements()) {
            if (methodSize > 0) {
                urlString += "&";
            }
            methodSize++;

            String paraName = (String) enu.nextElement();
            String paraValue = "";
            String[] values = request.getParameterValues(paraName);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    if (i > 0) {
                        paraValue += ",";
                    }
                    paraValue += values[i];
                }
            } else {
                paraValue = request.getParameter(paraName);
            }
            urlString += paraName + "=" + paraValue;
        }
        return urlString;
    }
}
