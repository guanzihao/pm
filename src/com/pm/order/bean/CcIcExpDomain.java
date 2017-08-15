package com.pm.order.bean;

import com.pm.order.util.PoiExcelBaseBean;
/**
 * 导出实体类excel
 * @author Administrator
 *
 */
public class CcIcExpDomain extends PoiExcelBaseBean{
    
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
     * 报关单号
     */
    private String customCl;
    
    /**
     * 品名
     */
    private String itemName;
    
    /**
     * 件数
     */
    private String items;
    
    /**
     * 毛重
     */
    private String gross;
    
    /**
     * 体积(????)
     */
    private String volume;
    
    /**
     * 船名
     */
    private String shipName;
    
    /**
     * 航次
     */
    private String flight;
    
    /**
     *  提运单号
     */
    private String wayBillNo;
    
    /**
     * 建单时间(接单时间)
     */
    private String orderReceivingDate;
    
    /**
     * 车辆到达时间(接单登记时间)
     */
    private String bussinessDate;
    
    /**
     * 入库签收时间(入库完成时间)
     */
    private String storedFinishDate;
    
    /**
     * 理货单上传时间(???)
     */
    private String transDate;
    
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

    public String getCustomCl() {
        return customCl;
    }

    public void setCustomCl(String customCl) {
        this.customCl = customCl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
    }

    public String getOrderReceivingDate() {
        return orderReceivingDate;
    }

    public void setOrderReceivingDate(String orderReceivingDate) {
        this.orderReceivingDate = orderReceivingDate;
    }

    public String getBussinessDate() {
        return bussinessDate;
    }

    public void setBussinessDate(String bussinessDate) {
        this.bussinessDate = bussinessDate;
    }

    public String getStoredFinishDate() {
        return storedFinishDate;
    }

    public void setStoredFinishDate(String storedFinishDate) {
        this.storedFinishDate = storedFinishDate;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getProjectAccomplishDate() {
        return projectAccomplishDate;
    }

    public void setProjectAccomplishDate(String projectAccomplishDate) {
        this.projectAccomplishDate = projectAccomplishDate;
    }
}
