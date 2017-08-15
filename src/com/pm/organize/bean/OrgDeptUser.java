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
 * 部门对应人员信息
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_ORGANIZE_OrgDeptUser")
public class OrgDeptUser extends MetaObject {

    /**
     * 部门
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "org_Dept")
    private OrgDept orgDept;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_Account")
    private UserAccount userAccount;
    
    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CompanyInfo_User")
    private CompanyInfoUser companyInfoUser;

    public CompanyInfoUser getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(CompanyInfoUser companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public OrgDept getOrgDept() {
        return orgDept;
    }

    public void setOrgDept(OrgDept orgDept) {
        this.orgDept = orgDept;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getEntityView() {
        return "部门人员";
    }
}
