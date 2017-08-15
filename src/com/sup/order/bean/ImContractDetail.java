package com.sup.order.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 进口合同明细
 * @author Chu Zhaocheng
 * @date 2017年6月25日 下午6:36:56
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PM_IMPORT_CONTRACT_DETAIL")
public class ImContractDetail extends MetaObject {

    /**
     * 进口合同ID
     */
    @Column(name = "im_contract_id")
    private String imContractId;
    /**
     * 商品Id
     */
    @Column(name = "commodity_Id")
	private String commodityId;
    
    /**
     * 商品名称
     */
    @Column(name = "commodity_name")
    private String commodityName;
    
	/**
	 * 商品型号
	 */
    @Column(name = "model")
	private String model;
	/**
	 * 商品数量
	 */
    @Column(name = "quantity")
	private Integer quantity;
	/**
	 * 商品单价
	 */
    @Column(name = "unit_price")
	private Double unitPrice;
	/**
	 * 商品总价
	 */
    @Column(name = "total_amount")
	private Double totalAmount;
	
    
	
	public String getCommodityId() {
        return commodityId;
    }
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
    public String getImContractId() {
        return imContractId;
    }
    public void setImContractId(String imContractId) {
        this.imContractId = imContractId;
    }
}
