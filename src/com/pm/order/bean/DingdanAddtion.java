package com.pm.order.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 订单补充资料
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PM_OrderAddtion")
public class DingdanAddtion extends MetaObject{
    
    /**
     * 订单id
     */
    @Column(name = "order_ID")
    private String orderID;
    
    /**
     * 订单类型
     */
    @Column(name = "order_type")
    private String ordertype;
    
    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createuser;
    
    /**
     * 内容
     */
    @Column(name = "content")
    private String content;
    
    /**
     * 附件
     */
    @Column(name = "files")
    private String files;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    
}
