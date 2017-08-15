package com.sup.company.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pm.company.bean.CompanyInfoUser;
import com.sup.company.controller.SupBaseController;

/**
 * 判断供应商是否登录
 * 
 * @author youliang.fang
 */

public class SupSessionInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CompanyInfoUser userAccount = (CompanyInfoUser) request.getSession().getAttribute(SupBaseController.SUP_SESSION);
        if (userAccount == null) {
            request.getRequestDispatcher("/error/error-nosession.jsp").forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
