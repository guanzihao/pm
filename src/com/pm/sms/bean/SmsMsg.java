package com.pm.sms.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 短消息记录
 * 
 * @author ZZY_SIVE
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_SMS_SmsMsg")
public class SmsMsg extends MetaObject {
    
    /**
     * 接收的手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 发送时间
     */
    @Column(name = "submitDate")
    private Date submitDate;

    /**
     * 短信平台放回MsgID
     */
    @Column(name = "msgId")
    private String msgId;

    /**
     * 发送状态（0：已提交 1:发送成功 2:发送失败）
     */
    @Column(name = "smsState")
    private int smsState;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getSmsState() {
        return smsState;
    }

    public void setSmsState(int smsState) {
        this.smsState = smsState;
    }

    @Override
    public String getEntityView() {
        return "短信记录";
    }
}
