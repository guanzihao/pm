package com.sup.order.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * 采购进口订单明细
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PURCHASE_ORDER_PRODUCTION_Detail")
public class PurchaseOrderProductionDetail extends MetaObject{
    /**
     * 明细编号
     */
    @Column(name = "detail_no")
    private String detailNo;
    /**
     * 描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 计算单位
     */
    @Column(name = "unit")
    private String unit;
    
    /**
     * 价格有效期
     */
    @Column(name = "price_vilidity_period")
    private String priceVilidityPeriod;
    /**
     * 单价
     */
    @Column(name = "per_price")
    private Double perPrice;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    
    public String getDetailNo() {
        return detailNo;
    }
    public void setDetailNo(String detailNo) {
        this.detailNo = detailNo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getPriceVilidityPeriod() {
        return priceVilidityPeriod;
    }
    public void setPriceVilidityPeriod(String priceVilidityPeriod) {
        this.priceVilidityPeriod = priceVilidityPeriod;
    }
    public Double getPerPrice() {
        return perPrice;
    }
    public void setPerPrice(Double perPrice) {
        this.perPrice = perPrice;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
