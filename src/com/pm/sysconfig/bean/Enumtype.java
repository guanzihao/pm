package com.pm.sysconfig.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 接口表
 * 
 * @author ZZY_SIVE
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Enum_Type")
public class Enumtype extends MetaObject {

    
    /**
     * 类型名称
     */
    @Column(name = "name",length=100)
    private String name;
    
    /**
     * 类型名称
     */
    @Column(name = "comCode",length=100)
    private String comCode;

    /**
     *显示顺序
     */
    @Column(name = "sort",length=10)
    private String sort;
    
    
    /**
     *是否启用
     */
    @Column(name = "status",length=1)
    private String status;
    
    

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    
    public String getSort() {
        return sort;
    }
    public void setSort(String Sort) {
        this.sort = Sort;
    }
    
 
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String Status) {
        this.status = Status;
    }
   
}
