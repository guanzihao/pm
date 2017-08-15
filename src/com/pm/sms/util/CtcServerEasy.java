package com.pm.sms.util;

import org.apache.log4j.Logger;

import com.bcloud.msg.http.HttpSender;
import com.pm.core.util.StringUtil;
import com.pm.sms.bean.SmsConfig;
import com.pm.sms.bean.SmsMsg;

/**
 * 功能：短信发送功能
 */

public class CtcServerEasy {
    private static Logger log = Logger.getLogger(CtcServerEasy.class);

    /**
     * 发送短信息
     * 
     * @param smsMsg
     */
    public static void sendSms(SmsConfig smsConfig, SmsMsg smsMsg) {
        if (smsConfig != null && smsMsg != null) {
            try {
                String returnString = HttpSender.batchSend(smsConfig.getUrl(), smsConfig.getAccount(), smsConfig.getPassword(), smsMsg.getPhone(), smsMsg.getContent(), true, null, null);
                if (!StringUtil.isEmpty(returnString)) {
                    String[] strings = returnString.split("\n");
                    if (strings != null) {
                        String state = strings[0].split(",")[1];
                        if (state.equals("0")) {
                            smsMsg.setMsgId(strings[1]);
                            log.info("send phone:" + smsMsg.getPhone() + ", " + strings[1]);
                        }
                    }
                }
                log.info("send phone:" + smsMsg.getPhone() + ", " + returnString);
            } catch (Exception e) {
                log.info(e, e);
            }
        }
    }
}
