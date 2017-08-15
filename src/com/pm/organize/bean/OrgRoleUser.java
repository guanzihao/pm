package com.pm.organize.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.bean.MetaObject;

/**
 * 组织结构，角色信息
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_ORGANIZE_OrgRoleUser")
public class OrgRoleUser extends MetaObject {

    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "org_Role")
    private OrgRole orgRole;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_Account")
    private UserAccount userAccount;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyInfo_User")
    private CompanyInfoUser companyInfoUser;

    public CompanyInfoUser getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(CompanyInfoUser companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public OrgRole getOrgRole() {
        return orgRole;
    }

    public void setOrgRole(OrgRole orgRole) {
        this.orgRole = orgRole;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getEntityView() {
        return "角色人员";
    }
}
