package com.sup.integrate.bean;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;
import com.pm.integrate.bean.Integrate;

/**
 * 用户集成登录账号
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PM_IntegLogins")
public class Integrates extends MetaObject{

    private String integLogins;
    /**
     * 系统信息
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "IntegLogin")
    private Integrate integrate;
    
    /**
     * 账号参数名
     */
    @Column(name = "login_usercode")
    private String loginusercode;
    
    /**
     * 密码参数名
     */
    @Column(name = "login_password")
    private String loginpassword;
    
    /**
     * 客户账号
     */
    @Column(name = "userAccount")
    private String userAccount;

    /**
     * 是否启用(Y通过)（驳回）
     */
    @Column(name = "login_state", length = 1)
    private String loginstate;

    
    public String getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(String loginstate) {
        this.loginstate = loginstate;
    }




    public String getIntegLogins() {
        return integLogins;
    }

    public void setIntegLogins(String integLogins) {
        this.integLogins = integLogins;
    }

    public Integrate getIntegrate() {
        return integrate;
    }

    public void setIntegrate(Integrate integrate) {
        this.integrate = integrate;
    }

    public String getLoginusercode() {
        return loginusercode;
    }

    public void setLoginusercode(String loginusercode) {
        this.loginusercode = loginusercode;
    }

    public String getLoginpassword() {
        return loginpassword;
    }

    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
