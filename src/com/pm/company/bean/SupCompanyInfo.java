package com.pm.company.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import com.pm.core.bean.MetaObject;

/**
 * 供应商公司信息
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PM_COMPANY_SupCompanyInfo")
public class SupCompanyInfo extends MetaObject {

    /**
     * 供应商公司名称
     */
    @Column(name = "comName", length = 100)
    private String comName;


    /**
     * 供应商公司地址
     */
    @Column(name = "comAddress", length = 100)
    private String comAddress;

    /**
     * 供应商编号
     */
    @Column(name = "comCode", length = 100)
    private String comCode;

    /**
     * 供应商电话
     */
    @Column(name = "comTel", length = 100)
    private String comTel;

    /**
     * 供应商邮箱
     */
    @Column(name = "comEmail", length = 100)
    private String comEmail;
    
 
    /**
     * 供应商启用还是禁用
     */
    @Column(name = "comState", length = 1)
    private String comState;

    /**
     * 供应商类型
     */
    @Column(name = "comType", length = 100)
    private String comType;
  
    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getComState() {
        return comState;
    }

    public void setComState(String comState) {
        this.comState = comState;
    }

    /**
     * 联系人
     */
    @OrderBy("createDate asc")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "supCompanyInfo")
    private List<CompanyInfoUser> companyInfoUsers;


    
  
    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public List<CompanyInfoUser> getCompanyInfoUsers() {
        return companyInfoUsers;
    }

    public void setCompanyInfoUsers(List<CompanyInfoUser> companyInfoUsers) {
        this.companyInfoUsers = companyInfoUsers;
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getComTel() {
        return comTel;
    }

    public void setComTel(String comTel) {
        this.comTel = comTel;
    }

    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    public String getEntityView() {
        return "供应商公司信息";
    }
    
}
