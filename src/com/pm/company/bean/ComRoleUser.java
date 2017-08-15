package com.pm.company.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;
import com.pm.organize.bean.OrgRole;

/**
 * 客户角色人员信息
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_COMPANY_ComRoleUser")
public class ComRoleUser extends MetaObject {

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
    @JoinColumn(name = "user_companyInfoUser")
    private CompanyInfoUser companyInfoUser;
    
    public OrgRole getOrgRole() {
        return orgRole;
    }

    public void setOrgRole(OrgRole orgRole) {
        this.orgRole = orgRole;
    }

    public CompanyInfoUser getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(CompanyInfoUser companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public String getEntityView() {
        return "客户角色人员信息";
    }
}
