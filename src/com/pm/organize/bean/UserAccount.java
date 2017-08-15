package com.pm.organize.bean;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pm.core.bean.MetaObject;

/**
 * 用户账号
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_ORGANIZE_UserAccount")
public class UserAccount extends MetaObject {

    /**
     * 邮箱(登录名)
     */
    @Column(name = "user_Mail", length = 100)
    private String userMail;

    /**
     * 登录密码
     */
    @Column(name = "login_Pwd", length = 50)
    private String loginPwd;

    /**
     * 姓名(昵称)
     */
    @Column(name = "user_Name", length = 100)
    private String userName;

    /**
     * 手机号码
     */
    @Column(name = "user_Tel", length = 20)
    private String userTel;

    /**
     * 状态（W:待审批 Y:已通过 N:已驳回）
     */
    @Column(name = "user_State", length = 1)
    private String userState;

    /**
     * 是否超级管理员
     */
    @Column(name = "user_Admin")
    private boolean userAdmin;

    /**
     * 权限集合
     */
    @Transient
    private HashSet<String> authorityList = new HashSet<String>();

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public boolean getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(boolean userAdmin) {
        this.userAdmin = userAdmin;
    }

    public HashSet<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(HashSet<String> authorityList) {
        this.authorityList = authorityList;
    }

    public boolean auth(String authCode) {
        if (authorityList != null && !authorityList.isEmpty()) {
            return authorityList.contains(authCode);
        }
        return false;
    }

    public String getEntityView() {
        return "人员信息[账户]";
    }
}
