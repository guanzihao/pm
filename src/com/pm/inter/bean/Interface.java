package com.pm.inter.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    
    

    public String getIntername() {
        return intername;
    }

    public void setIntername(String intername) {
        this.intername = intername;
    }

    public String getIntercontent() {
        return intercontent;
    }

    public void setIntercontent(String intercontent) {
        this.intercontent = intercontent;
    }

    public String getIntersort() {
        return intersort;
    }

    public void setIntersort(String intersort) {
        this.intersort = intersort;
    }

    public String getIntercheckState() {
        return intercheckState;
    }

    public void setIntercheckState(String intercheckState) {
        this.intercheckState = intercheckState;
    }

    public String getInterstatus() {
        return interstatus;
    }

    public void setInterstatus(String interstatus) {
        this.interstatus = interstatus;
    }

   
    
    
   
   
}
