package com.pm.framework.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;
import com.pm.organize.bean.UserAccount;

/**
 * 系统访问日志记录
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_FRAMEWORK_MethodLog")
public class MethodLog extends MetaObject {

    /**
     * IP地址
     */
    @Column(name = "ipInfo")
    private String ipInfo;

    /**
     * 访问方法
     */
    @Column(name = "logMethod")
    private String logMethod;

    /**
     * 处理的URL
     */
    @Column(name = "logUrl", length = 5000)
    private String logUrl;

    /**
     * 访问人
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "userAccount")
    private UserAccount userAccount;

    public String getIpInfo() {
        return ipInfo;
    }

    public void setIpInfo(String ipInfo) {
        this.ipInfo = ipInfo;
    }

    public String getLogMethod() {
        return logMethod;
    }

    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getEntityView() {
        return "系统访问日志记录";
    }
}
