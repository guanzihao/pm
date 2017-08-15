package com.sup.company.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.framework.controller.FileBaseController;
import com.pm.organize.busin.OrganizeBusin;

public class SupBaseController extends FileBaseController {
    public static final String SUP_SESSION = "PM_SUP_SESSION_KEY";
    public static final String SUP_SESSION_OPENID = "PM_SUP_SESSION_KEY_OPENID";
    
    @Autowired
    public OrganizeBusin organizeBusin;
    
    @Autowired
    public FrameworkBusin frameworkBusin;

    /**
     * 当前登录的供应商
     */
    public CompanyInfoUser getCurrSup() {
        return (CompanyInfoUser) getSessionAttribute(SUP_SESSION);
    }

    /**
     * 存放当前登录的供应商信息
     */
    public void setCurrSup(CompanyInfoUser companyInfoUser) {
        request.getSession().setAttribute(SUP_SESSION, companyInfoUser);
    }
    
    /**
     * 当前登录的供应商OpenId
     */
    public String getCurrSupOpenId() {
        return (String)getSessionAttribute(SUP_SESSION_OPENID);
    }

    /**
     * 存放当前登录的供应商OpenId
     */
    public void setCurrSupOpenId(String openId) {
        request.getSession().setAttribute(SUP_SESSION_OPENID, openId);
    }
}
