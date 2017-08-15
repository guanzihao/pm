package com.pm.framework.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 上传文件的拥有者
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_FRAMEWORK_UploadFileOwner")
public class UploadFileOwner extends MetaObject {

    /**
     * 文件对象
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_File")
    private UploadFile uploadFile;

    /**
     * 实体对象
     */
    @Column(name = "entity_className")
    private String className;

    /**
     * 实体类数据ID
     */
    @Column(name = "entity_metaId")
    private String metaId;
    
    /**
     * 实体列metaColums
     */
    @Column(name = "entity_metaColums")
    private String metaColums;
    
    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMetaId() {
        return metaId;
    }

    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getMetaColums() {
        return metaColums;
    }

    public void setMetaColums(String metaColums) {
        this.metaColums = metaColums;
    }

    public String getEntityView() {
        return "附件所属者";
    }
}
