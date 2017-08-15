package com.pm.chatroom.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.bean.MetaObject;
import com.pm.organize.bean.UserAccount;

/**
 * 聊天记录
 * 
 * @author ZZY_SIVE
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_CHATROOM_ChatRoom")
public class ChatRoom extends MetaObject {
    
    /**
     * 发送人【采购员】
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fromUserAccount")
    private UserAccount fromUserAccount;

    /**
     * 接收人【采购员】
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "toUserAccount")
    private UserAccount toUserAccount;

    /**
     * 发送人【客户】
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fromCompanyInfo")
    private CompanyInfo fromCompanyInfo;

    /**
     * 接收人【客户】
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "toCompanyInfo")
    private CompanyInfo toCompanyInfo;
    
    /**
     * 发送人【供应商】
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fromSupCompanyInfo")
    private SupCompanyInfo fromSupCompanyInfo;

    /**
     * 接收人【客户】
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "toSupCompanyInfo")
    private SupCompanyInfo toSupCompanyInfo;
    
    /**
     * 客户供应商实际发送人
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyInfoUser")
    private CompanyInfoUser companyInfoUser;

    /**
     * 发送时间
     */
    @Column(name = "submitDate")
    private Date submitDate;

    /**
     * 发送内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    @Column(name = "text")
    private String text;
    
    public UserAccount getFromUserAccount() {
        return fromUserAccount;
    }

    public void setFromUserAccount(UserAccount fromUserAccount) {
        this.fromUserAccount = fromUserAccount;
    }

    public UserAccount getToUserAccount() {
        return toUserAccount;
    }

    public void setToUserAccount(UserAccount toUserAccount) {
        this.toUserAccount = toUserAccount;
    }

    public CompanyInfo getFromCompanyInfo() {
        return fromCompanyInfo;
    }

    public void setFromCompanyInfo(CompanyInfo fromCompanyInfo) {
        this.fromCompanyInfo = fromCompanyInfo;
    }

    public CompanyInfo getToCompanyInfo() {
        return toCompanyInfo;
    }

    public void setToCompanyInfo(CompanyInfo toCompanyInfo) {
        this.toCompanyInfo = toCompanyInfo;
    }

    public SupCompanyInfo getFromSupCompanyInfo() {
        return fromSupCompanyInfo;
    }

    public void setFromSupCompanyInfo(SupCompanyInfo fromSupCompanyInfo) {
        this.fromSupCompanyInfo = fromSupCompanyInfo;
    }

    public SupCompanyInfo getToSupCompanyInfo() {
        return toSupCompanyInfo;
    }

    public void setToSupCompanyInfo(SupCompanyInfo toSupCompanyInfo) {
        this.toSupCompanyInfo = toSupCompanyInfo;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CompanyInfoUser getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(CompanyInfoUser companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public String getEntityView() {
        return "聊天记录";
    }
}
