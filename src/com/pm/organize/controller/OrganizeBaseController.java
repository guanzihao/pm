package com.pm.organize.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pm.framework.controller.FileBaseController;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.busin.OrganizeBusin;

public class OrganizeBaseController extends FileBaseController {
    public static final String USER_SESSION = "PM_USER_SESSION_KEY";
    
    public static final String user_SESSION_OPENID = "PM_USER_SESSION_KEY_OPENID";

    @Autowired
    public OrganizeBusin organizeBusin;

    /**
     * 获得当前登录人
     * 
     * @return UserAccount
     */
    public UserAccount getCurrUser() {
        return (UserAccount) getSessionAttribute(USER_SESSION);
    }

    /**
     * 存放当前登录人信息
     * 
     * @param request HttpServletRequest
     * @param userAccount UserAccount
     */
    public void setCurrUser(UserAccount userAccount) {
        request.getSession().setAttribute(USER_SESSION, userAccount);
    }

    /**
     * 检查数据层权限，是否允许访问
     * 
     * @param mataUser 数据层用户
     * @return true：允许
     */
    public boolean checkMetaRight(UserAccount mataUser) {
        return checkMetaRight(mataUser.getId());
    }
    
    /**
     * 当前登录的供应商OpenId
     */
    public String getCurrSupOpenId() {
        return (String)getSessionAttribute(user_SESSION_OPENID);
    }

    /**
     * 存放当前登录的供应商OpenId
     */
    public void setCurrSupOpenId(String openId) {
        request.getSession().setAttribute(user_SESSION_OPENID, openId);
    }

    /**
     * 检查数据层权限，是否允许访问
     * 
     * @param mataUserId 数据层用户ID
     * @return true：允许
     */
    public boolean checkMetaRight(String mataUserId) {
        UserAccount currUser = this.getCurrUser();
        if (currUser.getId().equals(mataUserId)) {
            return true;
        }
        List<UserAccount> userAccounts = organizeBusin.getUserAccountList(currUser);
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getId().equals(mataUserId)) {
                return true;
            }
        }
        return false;
    }
}
