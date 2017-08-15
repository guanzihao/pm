package com.pm.organize.util;

import java.security.MessageDigest;

import com.pm.core.util.StringUtil;

/**
 * MD5工具类
 * 
 * @author youliang.fang
 */

public class MD5Util {

    private static final String KEY = "_P_M_";

    /**
     * 字符串MD5加密
     * 
     * @param str 待加密字符
     * @return 返回加密字符
     */
    public static String sign(String str) {
        if (!StringUtil.isEmpty(str)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update((KEY + str).getBytes());
                byte[] results = md.digest();
                int i;
                StringBuffer buf = new StringBuffer();
                for (int offset = 0; offset < results.length; offset++) {
                    i = results[offset];
                    if (i < 0) {
                        i += 256;
                    }
                    if (i < 16) {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
                return buf.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
		System.out.println(sign("123456"));
	}
}
