package com.pm.core.interceptor;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

import com.pm.core.util.StringUtil;

/**
 * 防止XSS攻击
 * 
 * @author youliang.fang
 */

public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        chain.doFilter(new EncodingHttpServletRequest((HttpServletRequest) request), response);
    }

    private static class EncodingHttpServletRequest extends HttpServletRequestWrapper {
        public EncodingHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            String value = super.getParameter(xssEncode(name));
            if (value != null) {
                value = xssEncode(value);
            }
            return value;
        }

        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = xssEncode(values[i]);
                }
            }
            return values;
        }

        @Override
        public String getHeader(String name) {
            String value = super.getHeader(xssEncode(name));
            if (value != null) {
                value = xssEncode(value);
            }
            return value;
        }

        private String xssEncode(String s) {
            if (StringUtil.isEmpty(s)) {
                return s;
            }
            s = StringEscapeUtils.escapeSql(s);
            return stripXSS(s);
        }

        private String stripXSS(String value) {
            if (value != null) {

                //JS引用
                Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("script(.*?)", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");

                //JS方法
                scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("alert\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");

                //JS事件
                scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onmouse(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onblur(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onchange(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("ondblclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onkey(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onfocus(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onselect(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");

                //IFRAME
                scriptPattern = Pattern.compile("<iframe>(.*?)</iframe>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("</iframe>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("<iframe(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");

                //SRC
                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
                        | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
                        | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
            }
            return value;
        }
    }
}
