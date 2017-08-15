package com.pm.right.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.util.StringUtil;
import com.sup.company.controller.SupBaseController;

/**
 * 权限控制
 * 
 * @author youliang.fang
 */

public class AuthComTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    /**
     * 权限代码
     */
    private String authCode;

    public int doStartTag() throws JspException {
        boolean result = false;
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        CompanyInfoUser companyInfoUser = (CompanyInfoUser) request.getSession().getAttribute(SupBaseController.SUP_SESSION);
        if (companyInfoUser != null && !StringUtil.isEmpty(authCode)) {
            if (companyInfoUser.auth(authCode)) {
                result = true;
            } else if (companyInfoUser.getUserAdmin()) {
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
