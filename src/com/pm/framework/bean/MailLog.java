package com.pm.framework.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pm.core.bean.MetaObject;

/**
 * 发送邮件LOG
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_FRAMEWORK_MailLog")
public class MailLog extends MetaObject {

    /**
     * 邮件标题
     */
    @Column(name = "mail_Subject")
    private String mailSubject;

    /**
     * 收件人
     */
    @Column(name = "mail_ToId")
    private String mailToId;

    /**
     * 邮件内容
     */
    @Column(name = "mail_Body", length = 2000)
    private String mailBody;

    /**
     * 邮件附件
     */
    @Transient
    private List<UploadFile> mailAttFiles;

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailToId() {
        return mailToId;
    }

    public void setMailToId(String mailToId) {
        this.mailToId = mailToId;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public List<UploadFile> getMailAttFiles() {
        return mailAttFiles;
    }

    public void setMailAttFiles(List<UploadFile> mailAttFiles) {
        this.mailAttFiles = mailAttFiles;
    }
    
    public String getEntityView() {
        return "发送邮件日志";
    }
}
