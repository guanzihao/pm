package com.pm.organize.bean;

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

/**
 * 组织结构，部门信息
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_ORGANIZE_OrgDept")
public class OrgDept extends MetaObject {

    /**
     * 部门编号
     */
    @Column(name = "dept_Code", length = 50)
    private String deptCode;

    /**
     * 部门名称
     */
    @Column(name = "dept_Name", length = 100)
    private String deptName;

    /**
     * 上级部门
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_parent")
    private OrgDept parent;

    /**
     * 下级部门集合
     */
    @OrderBy("createDate asc")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "parent")
    private List<OrgDept> children;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public OrgDept getParent() {
        return parent;
    }

    public void setParent(OrgDept parent) {
        this.parent = parent;
    }

    public List<OrgDept> getChildren() {
        return children;
    }

    public void setChildren(List<OrgDept> children) {
        this.children = children;
    }

    public String getEntityView() {
        return "部门信息";
    }
}
