package com.pm.sms.busin;

import com.pm.sms.bean.SmsConfig;
import com.pm.sms.bean.SmsMsg;

public interface SmsBusin
{
    /**
     * SpringBean的名称
     */
    public static String SMSBUSIN = "smsBusinImpl";
    
	SmsConfig getSmsConfig();
	
	SmsMsg getSmsMsg(String msgid);

	void addSmsMsg(SmsMsg smsMsg);

	void sendSmsMsg(SmsMsg smsMsg);
}
