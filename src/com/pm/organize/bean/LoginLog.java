package com.pm.organize.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 系统登录日志信息
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_ORGANIZE_LoginLog")
public class LoginLog extends MetaObject {

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_Account")
    private UserAccount userAccount;

    /**
     * IP地址
     */
    @Column(name = "ip_Info", length = 20)
    private String ipInfo;

    /**
     * Host Info
     */
    @Column(name = "host_Info", length = 200)
    private String hostInfo;

    /**
     * 用户访问浏览器配置
     */
    @Column(name = "user_Browse", length = 20)
    private String userBrowse;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getIpInfo() {
        return ipInfo;
    }

    public void setIpInfo(String ipInfo) {
        this.ipInfo = ipInfo;
    }

    public String getHostInfo() {
        return hostInfo;
    }

    public void setHostInfo(String hostInfo) {
        this.hostInfo = hostInfo;
    }

    public String getUserBrowse() {
        return userBrowse;
    }

    public void setUserBrowse(String userBrowse) {
        this.userBrowse = userBrowse;
    }

    public String getEntityView() {
        return "系统登录日志";
    }
}
