package com.pm.core.tag;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pm.core.busin.GlobalContext;

/**
 * 动态执行busin方法
 * 
 * @author FYL
 */

@SuppressWarnings("rawtypes")
public class ExecuteTag extends BodyTagSupport {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ExecuteTag.class);

    private List<Object> paramList = new ArrayList<Object>();

    /**
     * bean
     */
    private String bean = null;

    /**
     * 方法名
     */
    private String method = null;

    /**
     * 对象
     */
    private Object value = null;

    private HashMap<String, Class> primitiveMap = new HashMap<String, Class>();

    public ExecuteTag() {
        primitiveMap.put("int", Integer.TYPE);
        primitiveMap.put("long", Long.TYPE);
        primitiveMap.put("double", Double.TYPE);
        primitiveMap.put("float", Float.TYPE);
        primitiveMap.put("short", Short.TYPE);
        primitiveMap.put("boolean", Boolean.TYPE);
        primitiveMap.put("byte", Byte.TYPE);
    }

    public void release() {
        if (paramList != null) {
            paramList.clear();
        }
        bean = null;
        method = null;
        value = null;
    }

    public int doStartTag() {
        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException {
        Object result = null;
        if (!StringUtils.isEmpty(this.bean) && !StringUtils.isEmpty(this.method)) {
            Object executer = null;
            try {
                executer = GlobalContext.getBean(bean);
            } catch (Exception ex) {
                throw new JspException("cannot load bean[" + bean + "]", ex);
            }
            result = invokeMethod(executer, this.method);
        }
        if (this.id != null) {
            this.saveObject(this.id, result);
        } else if (result != null) {
            LOGGER.info(result.toString());
        }
        release();
        return EVAL_PAGE;
    }

    /**
     * 调用Bean的某个方法
     */
    private Object invokeMethod(Object executer, String methodName) throws JspException {
        Method[] _methodInfos = executer.getClass().getMethods();
        Object[] _params = paramList.toArray();
        for (Method _methodInfo : _methodInfos) {
            String _methodName = _methodInfo.getName();
            if (_methodName.equals(methodName)) {
                Object[] _tempParams = _params.clone();
                boolean isok = checkMethod(_methodInfo, _tempParams);
                if (isok) {
                    Object lc_return = null;
                    try {
                        if (Modifier.isStatic(_methodInfo.getModifiers())) {
                            lc_return = _methodInfo.invoke(null, _tempParams);
                        } else {
                            if (value != null) {
                                lc_return = _methodInfo.invoke(value, _tempParams);
                            } else {
                                lc_return = _methodInfo.invoke(executer, _tempParams);
                            }
                        }
                    } catch (Exception ex) {
                        throw new JspException(ex);
                    }
                    return lc_return;
                }
            }
        }
        return null;
    }

    private void saveObject(String id, Object result) {
        if (!StringUtils.isEmpty(id)) {
            if (result == null) {
                this.pageContext.removeAttribute(id);
            } else {
                this.pageContext.setAttribute(id, result);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private boolean checkMethod(Method method, Object[] params) {
        boolean isok = true;
        Class[] _paramTypes = method.getParameterTypes();
        if (_paramTypes.length == params.length) {
            try {
                for (int j = 0; j < params.length; j++) {
                    ExecuteParamTag _paramTag = (ExecuteParamTag) params[j];
                    String lc_paramType = _paramTag.getType();
                    Object lc_paramValue = _paramTag.getValue();
                    boolean _primitive = true;
                    Class lc_paramClass = primitiveMap.get(lc_paramType);
                    if (lc_paramClass == null) {
                        _primitive = false;
                        lc_paramClass = Class.forName(lc_paramType);
                    }
                    if (!lc_paramClass.isAssignableFrom(lc_paramValue.getClass())) {
                        lc_paramValue = ConvertUtils.convert(String.valueOf(lc_paramValue), lc_paramClass);
                    }

                    Class target = _paramTypes[j];
                    if (target.isPrimitive()) {
                        if (target == Integer.TYPE) {
                            target = Integer.class;
                        } else if (target == Long.TYPE) {
                            target = Long.class;
                        } else if (target == Double.TYPE) {
                            target = Double.class;
                        } else if (target == Float.TYPE) {
                            target = Float.class;
                        } else if (target == Character.TYPE) {
                            target = Character.class;
                        } else if (target == Byte.TYPE) {
                            target = Byte.class;
                        } else if (target == Boolean.TYPE) {
                            target = Boolean.class;
                        }
                    }
                    if (_primitive || lc_paramClass.isAssignableFrom(target)) {
                        params[j] = lc_paramValue;
                    } else {
                        isok = false;
                        break;
                    }
                }
            } catch (Exception ex) {
                isok = false;
            }
        } else {
            isok = false;
        }
        return isok;
    }

    public void addParam(Object object) {
        paramList.add(object);
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
