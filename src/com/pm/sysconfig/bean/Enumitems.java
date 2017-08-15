package com.pm.sysconfig.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;


@SuppressWarnings("serial")
@Entity
@Table(name = "Enum_Item")
public class Enumitems extends MetaObject{
    /**
     * 项目名称
     */
    @Column(name = "name")
    private String name;

    /**
     *显示顺序
     */
    @Column(name = "sort")
    private String sort;
    
    /**
     *是否审核
     */
    @Column(name = "checkstate")
    private String checkState;
    
    /**
     *是否启用
     */
    @Column(name = "status")
    private String status;
    
    /**
     * 枚举类型
     * @return
     */
    @Column(name="type_ID")
    private String typeID;
 
    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

  
    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getSort() {
        return sort;
    }
    public void setSort(String Sort) {
        this.sort = Sort;
    }
    
    public String getCheckstate() {
        return checkState;
    }
    public void setCheckState(String CheckState) {
        this.checkState = CheckState;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String Status) {
        this.status = Status;
    }
}
