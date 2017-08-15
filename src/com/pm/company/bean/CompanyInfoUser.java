package com.pm.company.bean;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pm.core.bean.MetaObject;

/**
 * 公司联系人
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_COMPANY_CompanyInfoUser")
public class CompanyInfoUser extends MetaObject {

    /**
     * 客户公司信息
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyInfo")
    private CompanyInfo companyInfo;
    
    /**
     * 供应商公司信息
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supCompanyInfo")
    private SupCompanyInfo supCompanyInfo;

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
     * 联系人名称
     */
    @Column(name = "user_Name", length = 100)
    private String userName;

    /**
     * 手机号码
     */
    @Column(name = "user_Tel", length = 30)
    private String userTel;

    /**
     * 联系电话
     */
    @Column(name = "user_Number", length = 30)
    private String userNumber;

    /**
     * 传真
     */
    @Column(name = "user_Fax", length = 30)
    private String userFax;

    /**
     * 状态（Y:已通过 N:已驳回）
     */
    @Column(name = "user_State", length = 1)
    private String userState;

    /**
     * 是否主联系人
     */
    @Column(name = "user_Admin")
    private boolean userAdmin;
    
    /**
     * 审核通过日期
     */
    @Column(name = "check_date")
    private Date checkDate;
    
    /**
     * 权限集合
     */
    @Transient
    private HashSet<String> authorityList = new HashSet<String>();
    
    public Date getCheckDate() {
        return checkDate;
    }
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }
    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public SupCompanyInfo getSupCompanyInfo() {
        return supCompanyInfo;
    }

    public void setSupCompanyInfo(SupCompanyInfo supCompanyInfo) {
        this.supCompanyInfo = supCompanyInfo;
    }

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

    public String getUserFax() {
        return userFax;
    }

    public void setUserFax(String userFax) {
        this.userFax = userFax;
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

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
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
        return "公司联系人";
    }
}
