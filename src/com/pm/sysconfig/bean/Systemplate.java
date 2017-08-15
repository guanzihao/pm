package com.pm.sysconfig.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 邮件信息模板
 * @author hly
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Sys_template")
public class Systemplate extends MetaObject{
    /**
     * 模板名称
     */
    @Column(name = "template_name" ,length=200)
    private String tempname;

    /**
     * 模板内容
     */
    @Column(name = "template_content" ,length=2000)
    private String tempcontet;
    
    /**
     * 模板类型
     */
    @Column(name = "template_type" ,length=2000)
    private String temptype;
    
   
    /**
     * 模板是否启用
     */
    @Column(name = "template_status")
    private String tempstatus;

    public String getTempname() {
        return tempname;
    }

    public void setTempname(String tempname) {
        this.tempname = tempname;
    }

    public String getTempcontet() {
        return tempcontet;
    }

    public void setTempcontet(String tempcontet) {
        this.tempcontet = tempcontet;
    }
    
 
    public String getTemptype() {
        return temptype;
    }

    public void setTemptype(String temptype) {
        this.temptype = temptype;
    }

    public String getTempstatus() {
        return tempstatus;
    }

    public void setTempstatus(String tempstatus) {
        this.tempstatus = tempstatus;
    }

}
