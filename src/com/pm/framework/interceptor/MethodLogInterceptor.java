package com.pm.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pm.core.busin.BusinApi;
import com.pm.core.util.StringUtil;
import com.pm.framework.bean.MethodLog;
import com.pm.framework.util.GlobalUtil;
import com.pm.framework.util.IpUtil;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;

/**
 * 拦截系统访问方法（系统日志）
 * 
 * @author youliang.fang
 */

public class MethodLogInterceptor extends HandlerInterceptorAdapter {

    private static final String NOTHODS = "log.noMethod";

    @Autowired
    private BusinApi businApi;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler2 = (HandlerMethod) handler;
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute(OrganizeBaseController.USER_SESSION);
        if (handler2 == null || userAccount == null) {
            request.getRequestDispatcher("/error/error.jsp").forward(request, response);
            return false;
        } else {
            String className = handler2.getBeanType().getName();
            String methodName = handler2.getMethod().getName();

            boolean check = true;
            String nothods = GlobalUtil.getValue(NOTHODS);
            if (!StringUtil.isEmpty(nothods)) {
                String[] methods = nothods.split(",");
                for (String string : methods) {
                    if (StringUtil.isEmpty(methodName, string)) {
                        check = false;
                        break;
                    }
                }
            }
            if (check) {
                MethodLog log = new MethodLog();
                log.setIpInfo(IpUtil.getIpInfo(request));
                log.setLogMethod(className + "." + methodName);
                log.setUserAccount(userAccount);
                log.setLogUrl(IpUtil.getRequestParameters(request));
                businApi.save(log);
            }
        }
        return true;
    }
}
