package com.pm.inter.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.bean.MetaObject;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;

/**
 * 客户供应商接口表
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Sys_Interface_supplier")
public class InterfaceConfig extends MetaObject {
    
    /**
     * 实体类型
     */
    @Column(name = "si_accountType")
    private String accountTypeId;
    
    /**
     * 供应商
     */
    @Column(name = "si_supper")
    private String suppersId;
    /**
     * 客户
     */
    @Column(name = "si_company")
    private String companyId;
    /**
     * 接口ID
     */
    @Column(name = "interface")
    private String intefaceId;
    /**
     * 开启日期
     */
    @Column(name = "start_date")
    private Date startDate;
    /**
     * 结束日期
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 审核日期
     */
    @Column(name = "si_checkDate")
    private Date checkDate;
    /**
     * 审核状态
     */
    @Column(name = "si_checkState")
    private Integer checkState;
    /**
     * 接口地址
     */
    @Column(name = "si_url")
    private String interfaceUrl;
    /**
     * 接口连接方式
     */
    @Column(name = "si_joinType")
    private String joinType;
    /**
     * 备注信息
     */
    @Column(name = "si_content")
    private String content;
    /**
     * 是否启用
     */
    @Column(name = "si_status")
    private String status;
    /**
     * 显示顺序
     */
    @Column(name = "si_sort")
    private String sort;
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }
    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getSuppersId() {
        return suppersId;
    }

    public void setSuppersId(String suppersId) {
        this.suppersId = suppersId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIntefaceId() {
        return intefaceId;
    }

    public void setIntefaceId(String intefaceId) {
        this.intefaceId = intefaceId;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

   
    
    

}
