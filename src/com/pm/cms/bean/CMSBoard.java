package com.pm.cms.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 网站留言版
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CMS_Board")
public class CMSBoard extends MetaObject{
     /**
      * 公司名称
      */
    @Column(name="company")
    private String  company;
    /**
     * 姓名
     */
    @Column(name="name")
    private String  name;
    /**
     * 联系电话
     */
    @Column(name="tel")
    private String  tel;
    /**
     * 电子邮箱
     */
    @Column(name="email")
    private String  email;
    /**
     * 标题
     */
    @Column(name="stitle")
    private String  stitle;
    /**
     * 留言内容
     */
    @Column(name="content")
    private String  content;
    /**
     * 是否已查看
     */
    @Column(name="is_read")
    private Integer  isRead;
    /**
     * 查看日期
     */
    @Column(name="read_date")
    private Date  readDate;
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStitle() {
        return stitle;
    }
    public void setStitle(String stitle) {
        this.stitle = stitle;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getIsRead() {
        return isRead;
    }
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
    public Date getReadDate() {
        return readDate;
    }
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
   
    
    
}
