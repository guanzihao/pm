package com.pm.right.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;
import com.pm.organize.bean.OrgRole;

/**
 * 系统菜单权限
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_RIGHT_AuthRole")
public class AuthRole extends MetaObject {

    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_Role")
    private OrgRole orgRole;

    /**
     * 权限
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authority")
    private Authority authority;

    public OrgRole getOrgRole() {
        return orgRole;
    }

    public void setOrgRole(OrgRole orgRole) {
        this.orgRole = orgRole;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public String getEntityView() {
        return "系统权限分配表";
    }
}
