package com.pm.order.bean;

import com.pm.order.util.PoiExcelBaseBean;
/**
 * 导出实体类excel
 * @author Administrator
 *
 */
public class WlEcExpDomain extends PoiExcelBaseBean{
    
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
     * 提货地址
     */
    private String pickupAddr;
    
    /**
     * 接单日期
     */
    private String orderReceivingDate;
    
    /**
     * 预计船期（？？）
     */
    private String acceptDate1;
    
    /**
     * 做箱日期（？？）
     */
    private String acceptDate2;
    
    /**
     *  报关放行日期
     */
    private String releaseDate;
    
    /**
     * 出运日期
     */
    private String shippingDate;
    
    
    /**
     * 经营单位(待定)
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


    public String getPickupAddr() {
        return pickupAddr;
    }


    public void setPickupAddr(String pickupAddr) {
        this.pickupAddr = pickupAddr;
    }


    public String getOrderReceivingDate() {
        return orderReceivingDate;
    }


    public void setOrderReceivingDate(String orderReceivingDate) {
        this.orderReceivingDate = orderReceivingDate;
    }


    public String getAcceptDate1() {
        return acceptDate1;
    }


    public void setAcceptDate1(String acceptDate1) {
        this.acceptDate1 = acceptDate1;
    }


    public String getAcceptDate2() {
        return acceptDate2;
    }


    public void setAcceptDate2(String acceptDate2) {
        this.acceptDate2 = acceptDate2;
    }


    public String getReleaseDate() {
        return releaseDate;
    }


    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    public String getShippingDate() {
        return shippingDate;
    }


    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
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
