package com.pm.noticeinformed.bean;

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

import com.pm.core.bean.MetaObject;
import com.pm.organize.bean.UserAccount;

/**
 * 公共通知
 * 
 * @author ZZY_SIVE
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_NOTICEINFORMED_NoticeInformed")
public class NoticeInformed extends MetaObject {

    /**
     * 标题
     */
    @Column(name = "notice_Title")
    private String noticeTitle;

    /**
     * 内容
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    @Column(name = "notice_Text")
    private String noticeText;
    
    /**
     * 所属类型 1所有人可见，2只平台人员可见，3只客户人员可见，4只供应商人员可见
     */
    @Column(name = "notice_Type")
    private int noticeType;
    
    /**
     * 状态
     */
    @Column(name = "notice_State", length = 1)
    private boolean noticeState;
    
    /**
     * 创建人
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_Account")
    private UserAccount userAccount;
    
    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public boolean isNoticeState() {
        return noticeState;
    }

    public void setNoticeState(boolean noticeState) {
        this.noticeState = noticeState;
    }

    public String getEntityView() {
        return "公共通知";
    }
}
