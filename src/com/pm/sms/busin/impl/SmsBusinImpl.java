package com.pm.sms.busin.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.sms.bean.SmsConfig;
import com.pm.sms.bean.SmsMsg;
import com.pm.sms.busin.SmsBusin;
import com.pm.sms.util.CtcServerEasy;

@Service
public class SmsBusinImpl implements SmsBusin {
    protected static final Logger LOGGER = Logger.getLogger(SmsBusinImpl.class);
    private static final String TD = "回复TD退订";

    @Autowired
    private BusinApi businApi;

    @Override
    public SmsConfig getSmsConfig() {
        return (SmsConfig) businApi.getQueryObject("from SmsConfig", null);
    }

    @Override
    public SmsMsg getSmsMsg(String msgid) {
        return (SmsMsg) businApi.getQueryObject("from SmsMsg where msgid = ?", new Object[] { msgid });
    }

    @Override
    public void addSmsMsg(SmsMsg smsMsg) {
        SmsConfig smsConfig = this.getSmsConfig();
        if (smsConfig != null) {
            CtcServerEasy.sendSms(smsConfig, smsMsg);
            businApi.save(smsMsg);
        }

    }

    @Override
    public void sendSmsMsg(SmsMsg smsMsg) {
        if (smsMsg.getContent().indexOf(TD) == -1) {
            smsMsg.setContent(smsMsg.getContent() + "." + TD);
        }
        businApi.save(smsMsg);
        this.sendSmsMsg(smsMsg);

    }

    /**
     * 解析具体回复信息，获得编号和报价
     */
    public String[] getProjectToPrice(String text) {
        String raString = "1234567890.";
        int length = text.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            String s = text.substring(i, i + 1);
            if (raString.indexOf(s) == -1) {
                count++;
            } else
                break;
        }
        return new String[] { text.substring(0, count), text.substring(count, length) };
    }

}
