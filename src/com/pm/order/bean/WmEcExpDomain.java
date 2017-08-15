package com.pm.order.bean;

import com.pm.order.util.PoiExcelBaseBean;
/**
 * 导出实体类excel
 * @author Administrator
 *
 */
public class WmEcExpDomain extends PoiExcelBaseBean{
    /**
     * 客户名称
     */
    private String name;
    
    /**
     * 业务流水号
     */
    private String taskNun;
    
    /**
     * 业务类型
     */
    private String taskType;
    
    /**
     * 品名
     */
    private String itemName;
    
    /**
     * 合同号
     */
    private String contractNum;
    
    /**
     * 合同币值
     */
    private String contractCurrency;
    
    /**
     * 合同金额
     */
    private String contractAmount;
    
    /**
     * 单证制作时间(外贸出口合同生效时间)
     */
    private String contractStartDate;
    
    /**
     * 信用证开证时间
     */
    private String lcStartDate;
    
    /**
     *  收汇时间
     */
    private String receiveDate;
    
    /**
     *出口清关时间(出运单生效时间)
     */
    private String wayBillDate;
    
    /**
     *结算开票时间(结算单生效日期)
     */
    private String balanceDate;
    
    /**
     *退税申请时间(退税申请单生效日期)
     */
    private String refundTime;
    
    /**
     * 预期完成日期
     */
    private String projectAccomplishDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskNun() {
        return taskNun;
    }

    public void setTaskNun(String taskNun) {
        this.taskNun = taskNun;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getContractCurrency() {
        return contractCurrency;
    }

    public void setContractCurrency(String contractCurrency) {
        this.contractCurrency = contractCurrency;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getLcStartDate() {
        return lcStartDate;
    }

    public void setLcStartDate(String lcStartDate) {
        this.lcStartDate = lcStartDate;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getWayBillDate() {
        return wayBillDate;
    }

    public void setWayBillDate(String wayBillDate) {
        this.wayBillDate = wayBillDate;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getProjectAccomplishDate() {
        return projectAccomplishDate;
    }

    public void setProjectAccomplishDate(String projectAccomplishDate) {
        this.projectAccomplishDate = projectAccomplishDate;
    }
}
