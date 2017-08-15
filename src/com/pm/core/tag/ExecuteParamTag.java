package com.pm.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ExecuteParamTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    /**
     * 参数类型
     */
    private String type = null;

    /**
     * 参数值
     */
    private Object value = null;

    public ExecuteParamTag() {
    }

    public int doStartTag() throws JspException {
        ExecuteTag _parent = (ExecuteTag) findAncestorWithClass(this, ExecuteTag.class);
        if (_parent == null) {
            throw new JspException("cannot find ExecuteTag as parent!");
        }
        _parent.addParam(this.clone());
        return SKIP_BODY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object clone() {
        ExecuteParamTag _tag = new ExecuteParamTag();
        _tag.value = this.value;
        _tag.type = this.type;
        return _tag;
    }
}
