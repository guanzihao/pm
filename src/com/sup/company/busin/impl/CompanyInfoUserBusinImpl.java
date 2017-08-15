package com.sup.company.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.busin.BusinApi;
import com.pm.notice.bean.Notice;
import com.pm.noticeinformed.bean.NoticeInformed;
import com.sup.company.busin.CompanyInfoUserBusin;

/**
 * 公司人员Service
 * 
 * @author FYL
 */

@Service
public class CompanyInfoUserBusinImpl implements CompanyInfoUserBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public CompanyInfoUser getCompanyInfoUser(String id) {
        return (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
    }

    @Override
    public CompanyInfoUser getCompanyInfoUserByMail(String userMail) {
        return (CompanyInfoUser) businApi.getQueryObject(" from CompanyInfoUser where userMail = ?", new Object[] { userMail });
    }

    @Override
    public CompanyInfoUser getSupCompanyInfoUser(String id) {
      
        return (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
    }
    
    /**
     * 查询公告通知信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<NoticeInformed> getNoticeInformed(){
        String sql="select top 5 obj_Id,notice_Title,obj_createDate from PM_NOTICEINFORMED_NoticeInformed where notice_Type=3"
                  +" order by obj_createDate desc";
        return (List<NoticeInformed>)businApi.getSQLQuery(sql, null).list();
    }
    /**
     * 查询站内消息信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Notice>  getNotice(String userId){
        String sql="SELECT TOP 5 notice.obj_Id obj_Id,notice_Text,notice.obj_createDate obj_createDate FROM PM_NOTICE_Notice notice "
                  +" left join PM_COMPANY_CompanyInfoUser u on notice.companyInfoUser=u.obj_Id"
                  +" left join PM_COMPANY_CompanyInfo com  on u.companyInfo=com.obj_Id"
                  +" where com.obj_Id=?"
                  +" ORDER BY convert(char(10),notice.obj_createDate,120) DESC";
      return (List<Notice>)businApi.getSQLQuery(sql, new Object[]{userId}).list();
        
    }
    /**
     * 查询服务商所能看到的站内消息数量
     * @param userId
     * @return
     */
    public Integer getSupplierNoticeCount(String userId){
        StringBuffer sql=new StringBuffer(" select count(*) from PM_NOTICE_Notice a"
                
                + " where a.notice_IsRead =0 and a.obj_Id in (select nu.user_Notice "
                
                + " from PM_NOTICE_NoticeUser nu where nu.companyInfoUser= ? ) ");
        return (Integer)businApi.getSQLQuery(sql.toString(), new Object[]{userId}).uniqueResult();
    }
    
    /**
     * 查询客户所能看到的站内消息数量
     * @param userId
     * @return
     */
    public Integer getCompanyNoticeCount(String userId){
                StringBuffer sql=new StringBuffer(" select count(*) from PM_NOTICE_Notice a"
                
                + " where a.notice_IsRead =0 and a.obj_Id in (select nu.user_Notice "
                
                + " from PM_NOTICE_NoticeUser nu where nu.companyInfoUser= ? ) ");
        return (Integer)businApi.getSQLQuery(sql.toString(), new Object[]{userId}).uniqueResult();
    }
    /**
     * 查询订单条数
     * @return
     */
    public Integer getComOrderNum(String userId){
        String sql="select count(1) from PM_OrderFrom o "
                  +" inner join PM_Task task on o.task_id=task.obj_Id "
                  +" inner join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id "
                  +" where task.user_id=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{userId}).uniqueResult();
    }
    /**
     * 查询已提交的统计订单数
     */
    public Integer  getTotalCountByYtj(String userId,Integer orderState){
        String sql="select count(1) from PM_OrderFrom o "
                +" inner join PM_Task task on o.task_id=task.obj_Id "
                +" inner join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id "
                +" where task.user_id=? and o.order_state=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{userId,orderState}).uniqueResult();
    }
    /**
     * 查询已完成的任务条数
     * @return
     */
    public Integer getTotalCountByYwc(String userId,Integer orderState){
        String sql="select count(1) from PM_OrderFrom o "
                +" inner join PM_Task task on o.task_id=task.obj_Id "
                +" inner join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id "
                +" where task.user_id=? and o.order_state=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{userId,orderState}).uniqueResult();
    }
    /**
     * 查询已分配的订单条数
     */
    public Integer getTotalCountByYfp(String userId,Integer orderState){
        String sql="select count(1) from PM_OrderFrom o "
                +" inner join PM_Task task on o.task_id=task.obj_Id "
                +" inner join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id "
                +" where task.user_id=? and o.order_state=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{userId,orderState}).uniqueResult();
    }
    /**
     * 按照本日汇总订单数 
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getNumByDay(String userId){
        String sql="select DATEPART(dd, o.obj_createDate) date,count(1) num"
                  +" FROM PM_OrderFrom o"
                  +" left join PM_Task task on o.task_id=task.obj_Id"
                  +" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id"
                  +" where datediff(week,o.obj_createDate,getdate())=0 and task.user_id=?"
                  +" GROUP By DATEPART(dd, o.obj_createDate)";
        return (List<Object[]>)businApi.getSQLQuery(sql,new Object[]{userId}).list();
        
    }
    /**
     * 按照本周汇总订单数
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object []> getOrderNumberByWeek(String userId){
        String sql="select DATEPART(ww, o.obj_createDate) date,count(1) num"
                +" FROM PM_OrderFrom o"
                +" left join PM_Task task on o.task_id=task.obj_Id"
                +" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id"
                +" where datediff(month,o.obj_createDate,getdate())=0"
                +" and task.user_Id=?"
                +" GROUP By DATEPART(ww, o.obj_createDate)";
      return  (List<Object []>)businApi.getSQLQuery(sql, new Object[]{userId}).list(); 
    }
    /**
     * 按照本月来汇总订单数
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getOrderNumberByMonth(String userId){
        String sql="select DATEPART(mm, o.obj_createDate) date,count(1) num"
                +" FROM PM_OrderFrom o"
                +" left join PM_Task task on o.task_id=task.obj_Id"
                +" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id"
                +" where datediff(year,o.obj_createDate,getdate())=0"
                +" and task.user_id=?"
                +" GROUP By DATEPART(mm, o.obj_createDate)";
      return  (List<Object[]>)businApi.getSQLQuery(sql, new Object[]{userId}).list();   
    }
    /**
     * 按照本季度汇总订单数
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getOrderNumberByQua(String userId){
        String sql="select DATEPART(quarter, o.obj_createDate) date,count(1) num"
                +" FROM PM_OrderFrom o"
                +" left join PM_Task task on o.task_id=task.obj_Id"
                +" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id"
                +" where datediff(year,o.obj_createDate,getdate())=0"
                +" and task.user_Id=?"
                +" GROUP By DATEPART(quarter, o.obj_createDate)";
       return (List<Object[]>)businApi.getSQLQuery(sql, new Object[]{userId}).list();
    }
    /**
     * 统计各类型的单据在总任务订单数所占的比重
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getBillProportion(String userId){
        String  sql="select b.flow_name name ,count(*) data from PM_OrderFrom a"
                +" left join PM_FlowType b on   a.tack_type_id=b.obj_Id"
                +" left join PM_Task task on a.task_id=task.obj_Id"
                +" left join PM_COMPANY_CompanyInfo com on task.user_id=com.obj_Id"
                +" where task.user_id=?"
                +" group by  b.flow_name";
      return (List<Object[]>)businApi.getSQLQuery(sql, new Object[]{userId}).list();
    }
    
    /**
     * 查询供应商的公告通知信息
     * @return 公告通知消息
     */
    @SuppressWarnings("unchecked")
    public List<NoticeInformed> getSupNoticeInformed(){
        String sql="select top 5 obj_Id,notice_Title,obj_createDate from PM_NOTICEINFORMED_NoticeInformed where notice_Type=4"
                  +" order by obj_createDate desc";
        return (List<NoticeInformed>)businApi.getSQLQuery(sql, null).list();
    }
    /**
     * 查询供应商的站内消息信息
     *  userId 供应商公司的编号
     * @return 站内消息
     */
    @SuppressWarnings("unchecked")
    public List<Notice>  getSupNotice(String userId){
        String sql="SELECT TOP 5 notice.obj_Id obj_Id,notice_Text,notice.obj_createDate obj_createDate FROM PM_NOTICE_Notice notice "
                  +" left join PM_COMPANY_CompanyInfoUser u on notice.companyInfoUser=u.obj_Id"
                  +" left join PM_COMPANY_SupCompanyInfo sup  on u.supCompanyInfo=sup.obj_Id"
                  +" where sup.obj_Id=?"
                  +" ORDER BY convert(char(10),notice.obj_createDate,120) DESC";
        return (List<Notice>)businApi.getSQLQuery(sql, new Object[]{userId}).list();
    }
    
    /**
     * 查询分配给供应商的订单数
     * userId 供应商公司的编号
     * @return
     */
    public Integer getSupOrderNum(String userId,Integer orderState){
        String sql="select count(*) from PM_OrderFrom o"
                  +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                  +" where o.supplier=? and o.order_state!=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{userId,orderState}).uniqueResult();
    }
    /**
     * 查询分配给供应商的已完成的订单条数
     */
    public Integer getSupTotalCountByYwc(String userId,Integer orderState){
        String sql="select count(1) from PM_OrderFrom o"
                  +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                  +" where o.supplier=? and o.order_state=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{userId,orderState}).uniqueResult();
    }
    
       
    /**
     * 查询分配给供应商的处理中的统计任务数
     *  userId 供应商公司的编号
     * @return 处理中的订单总数
     */
    public Integer  getSupTotalCountByYtj(String userId,Integer orderState){
        String sql="select count(*) from PM_OrderFrom o"
                  +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                  +" where  o.order_state=? and o.supplier=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{orderState,userId}).uniqueResult();
        
    }
    /**
     * 查询分配给供应商的已完成的任务条数
     *  userId 供应商公司的编号
     * @return 已完成的订单总数
     */
    public Integer getSupTotalCountByYfp(String userId,Integer orderState){
        String sql="select count(*) from PM_OrderFrom o"
                  +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                  +" where o.order_state=? and o.supplier=?";
        return (Integer)businApi.getSQLQuery(sql, new Object[]{orderState,userId}).uniqueResult();
    }
    
    /**
     * 查询按照本日汇总分配给供应商的订单数 
     * userId 客户公司的编号
     * @return 按照本日汇总订单数
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getSupNumByDay(String userId){
        String sql="select DATEPART(dd, o.obj_createDate) date,count(1) num"
                +" FROM PM_OrderFrom o"
                +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                +" where datediff(week,o.obj_createDate,getdate())=0 and o.supplier=?"
                +" GROUP By DATEPART(dd, o.obj_createDate)";
        return (List<Object[]>)businApi.getSQLQuery(sql,new Object[]{userId}).list();
    }
    /**
     * 查询按照本周汇总分配给供应商的订单数
     * userId 客户公司的编号
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object []> getSupOrderNumberByWeek(String userId){
        String sql="select DATEPART(ww, o.obj_createDate) date,count(1) num"
                +" FROM PM_OrderFrom o"
                +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                +" where datediff(month,o.obj_createDate,getdate())=0"
                +" and o.supplier=?"
                +" GROUP By DATEPART(ww, o.obj_createDate)";
        return (List<Object[]>)businApi.getSQLQuery(sql,new Object[]{userId}).list();
    }
    /**
     * 查询按照本月来汇总分配给供应商的订单数
     * userId 客户公司的编号
     * @return 按照本月来汇总订单数
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getSupOrderNumberByMonth(String userId){
        String sql="select DATEPART(mm, o.obj_createDate) date,count(1) num"
                +" FROM PM_OrderFrom o"
                +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                +" where datediff(year,o.obj_createDate,getdate())=0"
                +" and o.supplier=?"
                +" GROUP By DATEPART(mm, o.obj_createDate)";
        return (List<Object[]>)businApi.getSQLQuery(sql,new Object[]{userId}).list();
    }
    /**
     * 查询按照本季度汇总分配给供应商的订单数
     * @return 按照本季度汇总订单数
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getSupOrderNumberByQua(String userId){
        String sql="select DATEPART(quarter, o.obj_createDate) date,count(1) num"
                +" FROM PM_OrderFrom o"
                +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                +" where datediff(year,o.obj_createDate,getdate())=0"
                +" and o.supplier=?"
                +" GROUP By DATEPART(quarter, o.obj_createDate)";
        return (List<Object[]>)businApi.getSQLQuery(sql,new Object[]{userId}).list();
    }
    /**
     * 查询统计各类型的单据在分配给供应商的总任务订单数所占的比重
     * @return 统计各类型的单据在总任务订单数所占的比重
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> getSupBillProportion(String userId){
        String  sql="select b.flow_name name ,count(*) data from PM_OrderFrom o"
                +" left join PM_FlowType b on   o.tack_type_id=b.obj_Id"
                +" left join PM_COMPANY_SupCompanyInfo sup on o.supplier=sup.obj_Id"
                +" where o.supplier=?"
                +" group by  b.flow_name";
        return (List<Object[]>)businApi.getSQLQuery(sql,new Object[]{userId}).list();
        
    }

    @Override
    public CompanyInfo getCompanyInfo(String id) {
        CompanyInfoUser companyInfoUser= (CompanyInfoUser) businApi.get(CompanyInfoUser.class, id);
        CompanyInfo companyInfo=null;
        if (companyInfoUser!=null) {
            companyInfo =companyInfoUser.getCompanyInfo();
        }
        return companyInfo;
    }
}
