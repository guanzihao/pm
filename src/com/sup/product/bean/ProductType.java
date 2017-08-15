package com.sup.product.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 
 * @author shaobo.song
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PM_PRODUCT_ProductType")
public class ProductType extends MetaObject {
    /**
     * 名称
     */
    @Column(name = "type_Name", length = 100)
    private String typeName;

    /**
     * 编号
     */
    @Column(name = "type_Code", length = 100)
    private String code;

    /**
     * 上级目录
     */
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_parent")
    private ProductType parent;
    
    /**
     * 所属客户
     */
    @Column(name = "companyInfoUser")
    private String companyInfoUser;
    /**
     * 下级目录集合
     */
    @OrderBy("createDate asc")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "parent")
    private List<ProductType> children;

    public String getTypeName() {
        return typeName;
    }

    public String getCompanyInfoUser() {
        return companyInfoUser;
    }

    public void setCompanyInfoUser(String companyInfoUser) {
        this.companyInfoUser = companyInfoUser;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProductType getParent() {
        return parent;
    }

    public void setParent(ProductType parent) {
        this.parent = parent;
    }

    public List<ProductType> getChildren() {
        return children;
    }

    public void setChildren(List<ProductType> children) {
        this.children = children;
    }

    public String getEntityView() {
        return "产品目录信息";
    }
}
