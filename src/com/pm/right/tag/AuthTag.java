package com.pm.right.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.pm.core.util.StringUtil;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;

/**
 * 权限控制
 * 
 * @author youliang.fang
 */

public class AuthTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    /**
     * 权限代码
     */
    private String authCode;

    public int doStartTag() throws JspException {
        boolean result = false;
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute(OrganizeBaseController.USER_SESSION);
        if (userAccount != null && !StringUtil.isEmpty(authCode)) {
            if (userAccount.auth(authCode)) {
                result = true;
            } else if (userAccount.getUserAdmin()) {
                result = true;
            }
        }
        return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
