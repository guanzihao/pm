package com.pm.notice.bean;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.bean.MetaObject;
import com.pm.organize.bean.UserAccount;

/**
 * 站内消息
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_NOTICE_Notice")
public class Notice extends MetaObject {

    /**
     * 标题
     */
    @Column(name = "notice_Title")
    private String noticeTitle;
    
    /**
     * 发件人（客户供应商）
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "companyInfoUser")
    private CompanyInfoUser companyInfoUser;
    
    /**
     * 内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    @Column(name = "notice_Text")
    private String noticeText;
    
    /**
     * 是否包含附件（true:有）
     */
    @Column(name = "notice_IsFile")
    private boolean noticeIsFile;
    
    /**
     * 用户类型
     */
    @Column(name = "notice_type")
    private String noticetype;
    /**
     * 是否阅读（true:是）
     */
    @Column(name = "notice_IsRead")
    private boolean noticeIsRead;

    /**
     * 是否草稿（true:是）
     */
    @Column(name = "notice_Draft")
    private boolean noticeDraft;

    /**
     * 发件人
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_Account")
    private UserAccount userAccount;

    /**
     * 收件人集合
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "notice")
    private List<NoticeUser> noticeUserList;



    public CompanyInfoUser getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(CompanyInfoUser companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public String getNoticetype() {
        return noticetype;
    }

    public String isNoticetype() {
        return noticetype;
    }

    public void setNoticetype(String noticetype) {
        this.noticetype = noticetype;
    }


    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }

    public boolean getNoticeIsFile() {
        return noticeIsFile;
    }

    public void setNoticeIsFile(boolean noticeIsFile) {
        this.noticeIsFile = noticeIsFile;
    }

    public boolean getNoticeIsRead() {
        return noticeIsRead;
    }

    public void setNoticeIsRead(boolean noticeIsRead) {
        this.noticeIsRead = noticeIsRead;
    }

    public boolean getNoticeDraft() {
        return noticeDraft;
    }

    public void setNoticeDraft(boolean noticeDraft) {
        this.noticeDraft = noticeDraft;
    }

  

    public List<NoticeUser> getNoticeUserList() {
        return noticeUserList;
    }

    public void setNoticeUserList(List<NoticeUser> noticeUserList) {
        this.noticeUserList = noticeUserList;
    }

    public String getEntityView() {
        return "站内信息";
    }
}
