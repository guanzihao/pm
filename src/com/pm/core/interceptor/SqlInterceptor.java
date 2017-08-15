package com.pm.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pm.core.util.StringUtil;

/**
 * 防止SQL注入
 * 
 * @author youliang.fang
 */

public class SqlInterceptor extends HandlerInterceptorAdapter {

    /**
     * 定义关键字
     */
    private final String SQLFILTER = "'|and |exec |insert |select |delete |update |count |*|%|chr |mid |master |truncate |char |declare |or |+";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!checkSql(request.getParameter("searchName")) || !checkSql(request.getParameter("searchOrderName"))) {
            request.getRequestDispatcher("/resource/error-noright.jsp").forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    private boolean checkSql(String paramet) {
        if (!StringUtil.isEmpty(paramet)) {
            String[] sqlArr = SQLFILTER.split("\\|");
            for (String sql : sqlArr) {
                if (StringUtil.indexOf(paramet.toUpperCase(), sql.toUpperCase())) {
                    return false;
                }
            }
        }
        return true;
    }
}
