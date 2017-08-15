package com.pm.sms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 短信配置信息
 * 
 * @author ZZY_SIVE
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PM_SMS_SmsConfig")
public class SmsConfig extends MetaObject {

    // 标题【上海百酷】长度
    public static final int TITLE_LENGTH = 6;

    /**
     * 地址
     */
    @Column(name = "sms_url")
    private String url;

    /**
     * 用户名
     */
    @Column(name = "sms_account")
    private String account;

    /**
     * 密码
     */
    @Column(name = "sms_password")
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEntityView() {
        return "短信配置信息";
    }
}
