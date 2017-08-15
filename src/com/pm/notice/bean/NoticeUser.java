package com.pm.notice.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.bean.MetaObject;
import com.pm.organize.bean.UserAccount;

/**
 * 站内信息收件人
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_NOTICE_NoticeUser")
public class NoticeUser extends MetaObject {

    /**
     * 收件人
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_Account")
    private UserAccount userAccount;
    
    /**
     * 收件人（客户供应商）
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyInfoUser")
    private CompanyInfoUser companyInfoUser;
    
    /**
     * 通知系统
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_Notice")
    private Notice notice;

    public Notice getNotice() {
        return notice;
    }


    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public CompanyInfoUser getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(CompanyInfoUser companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public String getEntityView() {
        return "站内信息收件人";
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

   
    
}
