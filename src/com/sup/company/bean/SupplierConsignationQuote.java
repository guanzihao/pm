package com.sup.company.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;


/**
 * 供应商委托报价
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PM_Supplier_Consignation_Quote")
public class SupplierConsignationQuote extends MetaObject{
    
    /**
     * 类型
     */
    @Column(name = "supplier_type")
    private String supplierType;
    /**
     * 类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 地点
     */
    @Column(name = "site")
    private String site;

    /**
     * 目的地点
     */
    @Column(name = "purpose_place")
    private String purposePlace;
    
    /**
     * 起始地点
     */
    @Column(name = "origin_place")
    private String originPlace;
    
    /**
     * 进口
     */
    @Column(name = "enter_port")
    private String enterPort;
    
    /**
     * 出口
     */
    @Column(name = "export")
    private String export;
    
    /**
     * 服务商Id
     */
    @Column(name = "supplier_id")
    private String supplierId;
    
    /**
     * 价格
     */
    @Column(name = "price")
    private Double price;
    
    /**
     * 吨位
     */
    @Column(name = "tonnage")
    private Integer tonnage;
    
    /**
     * 是否审批
     */
    @Column(name="check_state")
    private Integer checkState;
    
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPurposePlace() {
        return purposePlace;
    }

    public void setPurposePlace(String purposePlace) {
        this.purposePlace = purposePlace;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getEnterPort() {
        return enterPort;
    }

    public void setEnterPort(String enterPort) {
        this.enterPort = enterPort;
    }

    public String getExport() {
        return export;
    }

    public void setExport(String export) {
        this.export = export;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTonnage() {
        return tonnage;
    }

    public void setTonnage(Integer tonnage) {
        this.tonnage = tonnage;
    }
    
}
