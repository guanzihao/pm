package com.pm.organize.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 组织结构，角色信息
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_ORGANIZE_OrgRole")
public class OrgRole extends MetaObject {

    /**
     * 角色编号
     */
    @Column(name = "role_Code", length = 50)
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "role_Name", length = 100)
    private String roleName;
    
    /**
     * 所属账号类型 1平台 2客户 3供应商
     */
    @Column(name = "role_Type", length = 100)
    private int roleType;
    
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getEntityView() {
        return "角色信息";
    }
}
