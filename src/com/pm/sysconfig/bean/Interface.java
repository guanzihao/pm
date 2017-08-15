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
@Table(name = "Sys_Interface")
public class Interface extends MetaObject {

    
    /**
     * 接口名称
     */
    @Column(name = "inter_name")
    private String intername;

    /**
     * 接口详情
     */
    @Column(name = "inter_content")
    private String intercontent;

    /**
     *显示顺序
     */
    @Column(name = "inter_sort")
    private String intersort;
    
    /**
     *是否审核
     */
    @Column(name = "inter_checkstate")
    private String intercheckState;
    
    /**
     *是否启用
     */
    @Column(name = "inter_status")
    private String interstatus;
    

    public String getInterName() {
        return intername;
    }

    public void setInterName(String inter_Name) {
        this.intername = inter_Name;
    }

    public String getInterContent() {
        return intercontent;
    }
    public void setInterContent(String inter_Content) {
        this.intercontent = inter_Content;
    }
    
    public String getInterSort() {
        return intersort;
    }
    public void setInterSort(String inter_Sort) {
        this.intersort = inter_Sort;
    }
    
    public String getInterCheckstate() {
        return intercheckState;
    }
    public void setInterCheckState(String inter_CheckState) {
        this.intercheckState = inter_CheckState;
    }
    
    public String getInterStatus() {
        return interstatus;
    }
    public void setStatus(String inter_Status) {
        this.interstatus = inter_Status;
    }
   
}
