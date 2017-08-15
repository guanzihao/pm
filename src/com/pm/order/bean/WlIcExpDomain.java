package com.pm.order.bean;

import com.pm.order.util.PoiExcelBaseBean;
/**
 * 导出实体类excel
 * @author Administrator
 *
 */
public class WlIcExpDomain extends PoiExcelBaseBean{
    
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
     * 件数
     */
    private String items;
    
    /**
     * 毛重
     */
    private String gross;
    
    /**
     * 体积
     */
    private String volume;
    
    /**
     * 送货地址
     */
    private String deliveryAddr;
    
    /**
     * 接单日期
     */
    private String acceptDate;
    
    /**
     * 换单日期
     */
    private String switchBillDate;
    
    
    /**
     *  报关放行日期
     */
    private String releaseDate;
    
    /**
     * 送货日期(出运时间)
     */
    private String shipmentDate;
    
    /**
     * 经营单位(待定?)
     */
    private String department;
    
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

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getSwitchBillDate() {
        return switchBillDate;
    }

    public void setSwitchBillDate(String switchBillDate) {
        this.switchBillDate = switchBillDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectAccomplishDate() {
        return projectAccomplishDate;
    }

    public void setProjectAccomplishDate(String projectAccomplishDate) {
        this.projectAccomplishDate = projectAccomplishDate;
    }
}
