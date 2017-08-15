package com.pm.task.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pm.company.bean.CompanyInfo;
import com.pm.core.bean.MetaObject;
import com.sup.order.bean.OrderFrom;

/**
 * 任务表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="PM_Task")
public class Task extends MetaObject{
    
    /**
     * 任务流水号
     */
    @Column(name="task_id")
    private String taskId;

    /**
     * 项目名称
     */
    @Column(name="task_name")
    private String taskName;

    /**
     * 开始日期
     */
    @Column(name="start_date")
    private Date startDate;

    /**
     * 结束日期
     */
    @Column(name="end_date")
    private Date endDate;
    /**
     * 计划开始日期
     */
    @Column(name="project_start_date")
    private Date projectStartDate;
    
    /**
     * 计划结束日期
     */
    @Column(name="project_end_date")
    private Date projectEndDate;
    /**
     * 任务状态
     */
    @Column(name="task_state")
    private Integer taskState;
    /**
     * 发布日期
     */
    @Column(name="issue_date")
    private Date issueDate;
    
    /**
     * 分配日期
     */
    @Column(name="allocation_date")
    private Date allocationDate;
    
    /**
     * 任务描述
     */
    @Column(name="task_desc")
    private String taskDesc;

    /**
     * 项目文件
     */
    @Column(name="task_file")
    private String taskFile;

    /**
     * 是否发布
     */
    @Column(name="is_issue")
    private String isIssue;

    /**
     * 是否审核
     */
    @Column(name="check_state")
    private Integer checkState;

    /**
     * 客户Id
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private CompanyInfo companyInfo;

    /**
     * 发布姓名
     */
    @Column(name="release_name")
    private String releaseName;

    /**
     * 任务类型
     */
    @Column(name="tack_type")
    private String tackType;

    /**
     *订单集合
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "task")
    private List<OrderFrom> orderFroms;
    
    /**
     * 最新节点
     */
    @Column(name = "node_id")
    private String nodeId;
    /**
     * 节点状态
     */
    @Column(name = "node_state")
    private String nodeState;
    /**
     * 节点时间
     */
    @Column(name = "node_date")
    private Date nodeDate;
    
    /**
     * 接单公司
     */
    @Column(name = "orderReceiving_company")
     private String orderReceivingCompany;
    /**
     * 是否已处理
     */
    @Column(name = "is_end")
    private String isCompelete;

    /**
     *任务类型
     */
    @Column(name = "task_classify")
    private String taskClassify;
    
    public String getNodeId() {
        return nodeId;
    }
   
    public String getTaskClassify() {
        return taskClassify;
    }

    public void setTaskClassify(String taskClassify) {
        this.taskClassify = taskClassify;
    }

    public String getOrderReceivingCompany() {
        return orderReceivingCompany;
    }

    public void setOrderReceivingCompany(String orderReceivingCompany) {
        this.orderReceivingCompany = orderReceivingCompany;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }

    public String getNodeState() {
        return nodeState;
    }

    public void setNodeState(String nodeState) {
        this.nodeState = nodeState;
    }

    public Date getNodeDate() {
        return nodeDate;
    }

    public void setNodeDate(Date nodeDate) {
        this.nodeDate = nodeDate;
    }

    
    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(String taskFile) {
        this.taskFile = taskFile;
    }

   
    
    public String getIsCompelete() {
        return isCompelete;
    }

    public void setIsCompelete(String isCompelete) {
        this.isCompelete = isCompelete;
    }

    public String getIsIssue() {
        return isIssue;
    }

    public void setIsIssue(String isIssue) {
        this.isIssue = isIssue;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }


    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getTackType() {
        return tackType;
    }

    public void setTackType(String tackType) {
        this.tackType = tackType;
    }

    public List<OrderFrom> getOrderFroms() {
        return orderFroms;
    }

    public void setOrderFroms(List<OrderFrom> orderFroms) {
        this.orderFroms = orderFroms;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }
}
