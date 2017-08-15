package com.pm.right.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_RIGHT_Authority")
public class Authority extends MetaObject {

    /**
     * 权限代码唯一标示
     */
    @Column(name = "auth_Code", length = 100)
    private String authCode;

    /**
     * 权限名称
     */
    @Column(name = "auth_Name", length = 100)
    private String authName;

    /**
     * 权限图标
     */
    @Column(name = "auth_Icon", length = 100)
    private String authIcon;

    /**
     * 权限链接
     */
    @Column(name = "auth_Url")
    private String authUrl;

    /**
     * 权限类型1:菜单 2:方法
     */
    @Column(name = "auth_Type")
    private int authType;

    /**
     * 显示排序
     */
    @Column(name = "auth_Index")
    private int authIndex;
    
    /**
     * 所属账号类型 1平台 2客户 3供应商
     */
    @Column(name = "auth_orgtype")
    private int orgtype;

    /**
     * 上级
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_Parent")
    private Authority parent;

    /**
     * 下级集合
     */
    @OrderBy("authIndex asc")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Authority> children;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthIcon() {
        return authIcon;
    }

    public void setAuthIcon(String authIcon) {
        this.authIcon = authIcon;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public int getAuthType() {
        return authType;
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public int getAuthIndex() {
        return authIndex;
    }

    public void setAuthIndex(int authIndex) {
        this.authIndex = authIndex;
    }

    public int getOrgtype() {
        return orgtype;
    }

    public void setOrgtype(int orgtype) {
        this.orgtype = orgtype;
    }

    public Authority getParent() {
        return parent;
    }

    public void setParent(Authority parent) {
        this.parent = parent;
    }

    public List<Authority> getChildren() {
        return children;
    }

    public void setChildren(List<Authority> children) {
        this.children = children;
    }

    public String getEntityView() {
        return "系统权限表";
    }
}
