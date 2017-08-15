package com.pm.framework.models;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检测重复提交
 * 
 * @author youliang.fang
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSubmit {
    boolean saveToken() default false;

    boolean removeToken() default false;
}
