package com.pm.framework.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 系统文件存储
 * 
 * @author youliang.fang
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "PM_FRAMEWORK_UploadFile")
public class UploadFile extends MetaObject {

    /**
     * 文件名
     */
    @Column(name = "file_Name")
    private String fileName;

    /**
     * 真实的文件名
     */
    @Column(name = "file_PathName")
    private String filePathName;

    /**
     * 文件大小
     */
    @Column(name = "file_Size")
    private double fileSize;
    
    @Column(name = "file_metaColums")
    private String metaColums;
    

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePathName() {
        return filePathName;
    }

    public void setFilePathName(String filePathName) {
        this.filePathName = filePathName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getMetaColums() {
        return metaColums;
    }

    public void setMetaColums(String metaColums) {
        this.metaColums = metaColums;
    }

    public String getEntityView() {
        return "附件存储表";
    }
}
