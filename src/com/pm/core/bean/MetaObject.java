package com.pm.core.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 抽象的MetaObject
 * 
 * @author FYL
 */

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class MetaObject implements Serializable {

    @Id
    @Column(name = "obj_Id", nullable = false, unique = true, length = 36)
    @GenericGenerator(name = "pm-uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "pm-uuid2")
    protected String id;

    @Column(name = "obj_createDate", nullable = false)
    protected Date createDate;

    @Column(name = "obj_updateDate", nullable = false)
    protected Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getEntityView() {
        return null;
    }
}
