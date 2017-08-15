package com.pm.framework.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 邮件配置
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_identifyingCode")
public class IdentifyingCode extends MetaObject {

    /**
     * 注册邮箱
     */
    @Column(name = "regist_email")
    private String registemail;

    /**
     * 验证码
     */
    @Column(name = "regist_code")
    private String registcode;

    public String getRegistemail() {
        return registemail;
    }

    public void setRegistemail(String registemail) {
        this.registemail = registemail;
    }

    public String getRegistcode() {
        return registcode;
    }

    public void setRegistcode(String registcode) {
        this.registcode = registcode;
    }
    
    
    
}
