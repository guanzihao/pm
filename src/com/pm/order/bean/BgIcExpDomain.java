package com.pm.order.bean;

import com.pm.order.util.PoiExcelBaseBean;
/**
 * 导出实体类excel
 * @author Administrator
 *
 */
public class BgIcExpDomain extends PoiExcelBaseBean{
    
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
    private String customNo;
    
    /**
     * 提运单号
     */
    private String waybillNo;
    
    /**
     * 船名/航次
     */
    private String flight;
    
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
     *  接单时间
     */
    private String bussinessDate;
    
    /**
     * 查验时间
     */
    private String inspectionDate;
    
    
    /**
     * 放行时间
     */
    private String releaseDate;
    
    /**
     * 预期完成日期
     */
    private String projectAccomplishDate;

    /**
     * 经营单位
     */
    private String opeComp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
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

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getBussinessDate() {
        return bussinessDate;
    }

    public void setBussinessDate(String bussinessDate) {
        this.bussinessDate = bussinessDate;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProjectAccomplishDate() {
        return projectAccomplishDate;
    }

    public void setProjectAccomplishDate(String projectAccomplishDate) {
        this.projectAccomplishDate = projectAccomplishDate;
    }

    public String getOpeComp() {
        return opeComp;
    }

    public void setOpeComp(String opeComp) {
        this.opeComp = opeComp;
    }
}
