package com.sup.flow.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 邮件获取数据表
 * @author hly
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PM_Model")
public class Model extends MetaObject{
    /**
     * 收件人
     */
    @Column(name="Com_Email")
    private String Email;
    
    /**
     * 邮件类型
     */
    @Column(name="Com_EmailType")
    private String EmailType;
    
    /**
     * 是否已发送
     */
    @Column(name="is_Email")
    private String isEmail;
    
    /**
     * 发送邮件的表名
     */
    @Column(name="table_name")
    private String tablename;
    
    /**
     * 发送邮件的表名
     */
    @Column(name="ok_time")
    private Date oktime;
    
    /**
     * objids
     */
    @Column(name="objids")
    private String objids;
    
    public String getObjids() {
        return objids;
    }

    public Date getOktime() {
        return oktime;
    }

    public void setOktime(Date oktime) {
        this.oktime = oktime;
    }

    public void setObjids(String objids) {
        this.objids = objids;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmailType() {
        return EmailType;
    }

    public void setEmailType(String emailType) {
        EmailType = emailType;
    }
    
}
