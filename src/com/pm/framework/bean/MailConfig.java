package com.pm.framework.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 邮件配置
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_FRAMEWORK_MailConfig")
public class MailConfig extends MetaObject {

    /**
     * SMTP服务器
     */
    @Column(name = "mail_Smtp", length = 100)
    private String mailSmtp;

    /**
     * 端口
     */
    @Column(name = "mail_Port", length = 50)
    private String mailPort;

    /**
     * 用户名
     */
    @Column(name = "mail_Name", length = 100)
    private String mailName;

    /**
     * 密码
     */
    @Column(name = "mail_Pwd", length = 50)
    private String mailPwd;

    /**
     * 延时时间
     */
    @Column(name = "mail_Timeout", length = 8)
    private String mailTimeout;

    /**
     * 身份验证
     */
    @Column(name = "mail_Auth")
    private boolean mailAuth;

    /**
     * SSL验证
     */
    @Column(name = "mail_Ssl")
    private boolean mailSsl;

    public String getMailSmtp() {
        return mailSmtp;
    }

    public void setMailSmtp(String mailSmtp) {
        this.mailSmtp = mailSmtp;
    }

    public String getMailPort() {
        return mailPort;
    }

    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getMailPwd() {
        return mailPwd;
    }

    public void setMailPwd(String mailPwd) {
        this.mailPwd = mailPwd;
    }

    public String getMailTimeout() {
        return mailTimeout;
    }

    public void setMailTimeout(String mailTimeout) {
        this.mailTimeout = mailTimeout;
    }

    public boolean getMailAuth() {
        return mailAuth;
    }

    public void setMailAuth(boolean mailAuth) {
        this.mailAuth = mailAuth;
    }

    public boolean getMailSsl() {
        return mailSsl;
    }

    public void setMailSsl(boolean mailSsl) {
        this.mailSsl = mailSsl;
    }

    public String getEntityView() {
        return "邮件配置";
    }
}
