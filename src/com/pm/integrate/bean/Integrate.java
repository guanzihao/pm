package com.pm.integrate.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;
import com.sup.integrate.bean.Integrates;


@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_IntegLogin")
public class Integrate extends MetaObject{
    
    /**
     * 名称
     */
    @Column(name = "login_name")
    private String loginname;
    
    /**
     *客户系统配置
     */
    @OrderBy("obj_createDate asc")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "integrate")
    private List<Integrates> integrates;
    
    /**
     * 内网地址
     */
    @Column(name = "login_innerurl")
    private String logininnerurl;
    
    /**
     * 外网地址
     */
    @Column(name = "login_outurl")
    private String loginouturl;
    
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
     * 提交方式
     */
    @Column(name = "login_postType")
    private String loginpostType;

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

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLogininnerurl() {
        return logininnerurl;
    }

    public void setLogininnerurl(String logininnerurl) {
        this.logininnerurl = logininnerurl;
    }

    public String getLoginouturl() {
        return loginouturl;
    }

    public void setLoginouturl(String loginouturl) {
        this.loginouturl = loginouturl;
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

    public String getLoginpostType() {
        return loginpostType;
    }

    public void setLoginpostType(String loginpostType) {
        this.loginpostType = loginpostType;
    }
    
}
