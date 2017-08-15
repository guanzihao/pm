package com.sup.product.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 
 * @author shaobo.song
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PM_PRODUCT_Product")
public class Product extends MetaObject {
    /**
     * 产品名称
     */
    @Column(name = "product_Name", length = 200)
    private String productName;

    /**
     * 产品编号
     */
    @Column(name = "product_Code", length = 50)
    private String productCode;

    /**
     * 产品规格型号
     */
    @Column(name = "product_Spec", length = 50)
    private String productSpec;


    /**
     * 产品单位
     */
    @Column(name = "product_Unit", length = 100)
    private String productUnit;

    /**
     * 产品价格
     */
    @Column(name = "product_Price")
    private double productPrice;
    
    /**
     * 产品是够上架(Y上架)N下架
     */
    @Column(name = "product_state",length=1)
    private String productstate;

    /**
     * 产品目录
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_productType")
    private ProductType productType;
    
    /**
     * 所属客户
     */
    @Column(name = "companyInfoUser")
    private String companyInfoUser;
    
    /**
     * 显示顺序
     */
    @Column(name = "product_Index")
    private int productIndex;

    public String getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(String companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public String getProductstate() {
        return productstate;
    }

    public void setProductstate(String productstate) {
        this.productstate = productstate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    public String getEntityView() {
        return "产品信息";
    }
}
