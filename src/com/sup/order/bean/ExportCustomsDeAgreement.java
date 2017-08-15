package com.sup.order.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pm.core.bean.MetaObject;

/**
 * @Description: 委托报关协议(报关出口)
 * @author Chu Zhaocheng
 * @date 2017年6月25日 下午6:57:11
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CUSTOMS_EXPORTDECLARATION_AGREEMENT")
public class ExportCustomsDeAgreement extends MetaObject {
    
    /**
     * 客户ID
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 任务ID
     */
    @Column(name = "task_id")
	private String taskId;
	/**
	 * 订单ID
	 */
    @Column(name = "order_from_id")
	private String orderFromId;
	/**
	 * 委托方
	 */
    @Column(name = "consignor")
	private String consignor;
	/**
	 * 货物名称
	 */
    @Column(name = "goods_name")
	private String goodsName;
	/**
	 * HS编码
	 */
    @Column(name = "hs_code")
	private String hsCode;
	/**
	 * 重量
	 */
    @Column(name = "weight")
	private Integer weight;
	/**
	 * 包装情况
	 */
    @Column(name = "pack_detail")
	private String packDetail;
	/**
	 * 合同号
	 */
    @Column(name = "contract_no")
	private String contractNo;
	/**
	 * 许可文件号
	 */
    @Column(name = "license_file_no")
	private String licenseFileNo;
	/**
	 * 货物总价
	 */
    @Column(name = "goods_total_price")
	private Double goodsTotalPrice;
	/**
	 * 进出口日期
	 */
    @Column(name = "im_ex_date")
	private Date imExDate;
	/**
	 * 提单号
	 */
    @Column(name = "delivery_number")
	private String deliveryNumber;
	/**
	 * 贸易方式
	 */
    @Column(name = "trade_type")
	private String tradeType;
	/**
	 * 货源地
	 */
    @Column(name = "origin_place")
	private String originPlace;
	/**
	 * 其他要求
	 */
    @Column(name = "others")
	private String others;
	/**
	 * 被委托方
	 */
    @Column(name = "consignee")
	private String consignee;
	/**
	 * 报关单编码
	 */
    @Column(name = "customs_code")
	private String customsCode;
	/**
	 * 收到单证日期
	 */
    @Column(name = "receive_docs_date")
	private Date receiveDocsDate;
	/**
	 * 收到单证情况
	 */
    @Column(name = "receive_docs_condition")
	private String receiveDocsCondition;
	/**
	 * 其他单证情况
	 */
    @Column(name = "docs_others")
	private String docsOthers;
	/**
	 * 报关收费
	 */
    @Column(name = "customs_charge")
	private String customsCharge;
	/**
	 * 承诺说明
	 */
    @Column(name = "com_illustration")
	private String comIllustration;
	
    /**
     * 是否全权委托 0:自助委托,1:全权委托
     */
    @Column(name = "is_delegation")
    private Integer isDelegation;
	
    /**
     * 开始时间
     */
    @Column(name = "start_date")
    private Date startDate;
    
    /**
     * 开始时间
     */
    @Column(name = "end_date")
    private Date endDate;
    
    /**
     * 备注说明
     */
    @Column(name = "explain")
    private String explain;
    
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getExplain() {
        return explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
	public Integer getIsDelegation() {
        return isDelegation;
    }
    public void setIsDelegation(Integer isDelegation) {
        this.isDelegation = isDelegation;
    }
    public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getOrderFromId() {
		return orderFromId;
	}
	public void setOrderFromId(String orderFromId) {
		this.orderFromId = orderFromId;
	}
	public String getConsignor() {
		return consignor;
	}
	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getPackDetail() {
		return packDetail;
	}
	public void setPackDetail(String packDetail) {
		this.packDetail = packDetail;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getLicenseFileNo() {
		return licenseFileNo;
	}
	public void setLicenseFileNo(String licenseFileNo) {
		this.licenseFileNo = licenseFileNo;
	}
	public Double getGoodsTotalPrice() {
		return goodsTotalPrice;
	}
	public void setGoodsTotalPrice(Double goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}
	public Date getImExDate() {
		return imExDate;
	}
	public void setImExDate(Date imExDate) {
		this.imExDate = imExDate;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getOriginPlace() {
		return originPlace;
	}
	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getCustomsCode() {
		return customsCode;
	}
	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}
	public Date getReceiveDocsDate() {
		return receiveDocsDate;
	}
	public void setReceiveDocsDate(Date receiveDocsDate) {
		this.receiveDocsDate = receiveDocsDate;
	}
	public String getReceiveDocsCondition() {
		return receiveDocsCondition;
	}
	public void setReceiveDocsCondition(String receiveDocsCondition) {
		this.receiveDocsCondition = receiveDocsCondition;
	}
	public String getDocsOthers() {
		return docsOthers;
	}
	public void setDocsOthers(String docsOthers) {
		this.docsOthers = docsOthers;
	}
	public String getCustomsCharge() {
		return customsCharge;
	}
	public void setCustomsCharge(String customsCharge) {
		this.customsCharge = customsCharge;
	}
	public String getComIllustration() {
		return comIllustration;
	}
	public void setComIllustration(String comIllustration) {
		this.comIllustration = comIllustration;
	}
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
	
}
