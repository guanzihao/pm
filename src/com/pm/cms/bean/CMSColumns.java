package com.pm.cms.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 网站栏目表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CMS_Columns")
public class CMSColumns extends MetaObject{
  /**
   * 栏目名称
   */
    @Column(name="name")
    private String name;
    /**
     * 栏目缩略图
     */
    @Column(name="img")
    private String img;
    /**
     * 栏目描述
     */
    @Column(name="descs")
    private String desc;
    /**
     * 栏目顺序
     */
    @Column(name="column_sort")
    private Integer columnSort;
    /**
     * 是否启用
     */
    @Column(name="column_status")
    private Integer columnStatus;
    
    /**
     *内容集合
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "cmsColumns")
    private List<CMSContent> cmsContents;
    
    public List<CMSContent> getCmsContents() {
        return cmsContents;
    }

    public void setCmsContents(List<CMSContent> cmsContents) {
        this.cmsContents = cmsContents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getColumnSort() {
        return columnSort;
    }

    public void setColumnSort(Integer columnSort) {
        this.columnSort = columnSort;
    }

    public Integer getColumnStatus() {
        return columnStatus;
    }

    public void setColumnStatus(Integer columnStatus) {
        this.columnStatus = columnStatus;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
