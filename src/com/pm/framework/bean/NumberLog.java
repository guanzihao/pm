package com.pm.framework.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 流水号记录器
 * 
 * @author FYL5728
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_FRAMEWORK_NumberLog")
public class NumberLog extends MetaObject {

    /**
     * 代码（唯一标示）
     */
    @Column(name = "number_Code", length = 20)
    private String numberCode;

    /**
     * 流水号格式
     * 
     * {num} 流水号，自增长 {date} 时间，yyyyMMdd
     */
    @Column(name = "number_Format", length = 50)
    private String numberFormat;

    /**
     * 流水号位数
     */
    @Column(name = "number_Length")
    private int numberLength;

    /**
     * 记录最后一个流水号
     */
    @Column(name = "number_Last", length = 100)
    private String numberLast;

    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    public int getNumberLength() {
        return numberLength;
    }

    public void setNumberLength(int numberLength) {
        this.numberLength = numberLength;
    }

    public String getNumberLast() {
        return numberLast;
    }

    public void setNumberLast(String numberLast) {
        this.numberLast = numberLast;
    }

    public String getEntityView() {
        return "流水号记录器";
    }
}
