package com.pm.sms.util;

import com.pm.core.util.StringUtil;

public class SmsCodeTool {
    public static String[] strings = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "T", "V", "W", "X", "Y" };

    /**
     * 输入一个Code，返回Code，规则是返回输入code的后一个code，例如：输入A，返回B 输入AB，返回AC
     */
    public static String getSmsCode(String code) {
        if (StringUtil.isEmpty(code)) {
            return strings[0];
        }
        int length = code.length();
        String end = code.substring(length - 1, length);
        int count = getIndex(end);
        if (count == (strings.length - 1)) {
            code = code.substring(0, length - 1) + strings[0];
            int index = 2;
            int zcount = 1;
            for (int i = 1; i < length; i++) {
                end = code.substring(length - index, length - index + 1);
                count = getIndex(end);
                if (count == (strings.length - 1)) {
                    code = code.substring(0, length - index) + strings[0] + code.substring(length - index + 1, length);
                    zcount++;
                } else {
                    code = code.substring(0, length - index) + strings[count + 1] + code.substring(length - index + 1, length);
                    break;
                }
                index++;
            }
            if (zcount == length) {
                code += strings[0];
            }
        } else {
            code = code.substring(0, length - 1) + strings[count + 1];
        }
        return code;
    }

    public static int getIndex(String c) {
        int count = 0;
        for (String string : strings) {
            if (!string.equals(c)) {
                count++;
            } else
                break;
        }
        return count;
    }
}
