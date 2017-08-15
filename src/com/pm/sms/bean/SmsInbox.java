package com.pm.sms.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 回复信息
 * 
 * @author ZZY_SIVE
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_SMS_SmsInbox")
public class SmsInbox extends MetaObject {

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 回复内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 接收时间
     */
    @Column(name = "submitDate")
    private Date submitDate;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getEntityView() {
        return "短信回复";
    }
}
