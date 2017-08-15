package com.pm.cms.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 帮助中心栏目
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="Help_Columns")
public class HelpColumns extends MetaObject{
     /**
      * 栏目名称
      */
    @Column(name="Name")
    private String  name;
    /**
     *上级栏目
     */
    @Column(name="column_parent")
    private String  columnparent;
    /**
     * 栏目缩略图
     */
    @Column(name="Img")
    private String  img;
    /**
     * 栏目描述
     */
    @Column(name="colnumDesc")
    private String  colnumDesc;
    /**
     * 栏目顺序
     */
    @Column(name="column_Sort")
    private int  columnSort;
    /**
     * 是否启用
     */
    @Column(name="column_status")
    private String  columnstatus;
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColumnparent() {
        return columnparent;
    }
    public void setColumnparent(String columnparent) {
        this.columnparent = columnparent;
    }
   
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getColnumDesc() {
        return colnumDesc;
    }
    public void setColnumDesc(String colnumDesc) {
        this.colnumDesc = colnumDesc;
    }
    public String getColumn_status() {
        return columnstatus;
    }
    public void setColumn_status(String columnstatus) {
        this.columnstatus = columnstatus;
    }
    public int getColumnSort() {
        return columnSort;
    }
    public void setColumnSort(int columnSort) {
        this.columnSort = columnSort;
    }
    public String getColumnstatus() {
        return columnstatus;
    }
    public void setColumnstatus(String columnstatus) {
        this.columnstatus = columnstatus;
    }
  
   
    
    
}
