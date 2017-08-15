package com.sup.company.busin;

import java.util.List;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.notice.bean.Notice;
import com.pm.noticeinformed.bean.NoticeInformed;

/**
 * 供应商用户管理
 * 
 * @author GuoCe
 */

public interface CompanyInfoUserBusin {

    /**
     * SpringBean的名称
     */
    public static String COMPANYINFOUSERBUSIN = "companyInfoUserBusinImpl";

    /**
     * 根据ID查询CompanyInfoUser
     * 
     * @param id ID
     * @return CompanyInfoUser
     */
    CompanyInfoUser getCompanyInfoUser(String id);
    
    
    /**
     * 根据ID查询CompanyInfoUser
     * 
     * @param id ID
     * @return CompanyInfoUser
     */
    CompanyInfoUser getSupCompanyInfoUser(String id);

    /**
     * 根据CODE查询CompanyInfoUser
     * 
     * @param userMail
     * @return CompanyInfoUser
     */
    CompanyInfoUser getCompanyInfoUserByMail(String userMail);
    /**
     * 查询公告通知信息
     * @return 公告通知消息
     */
    List<NoticeInformed> getNoticeInformed();
    /**
     * 查询站内消息信息
     * @return 站内消息
     */
    List<Notice>  getNotice(String userId);
    /**
     * 查询服务商所能看到的站内消息数量
     * @param userId
     * @return
     */
    Integer getSupplierNoticeCount(String userId);
   
    /**
     * 查询客户所能看到的站内消息数量
     * @param userId
     * @return
     */
    Integer getCompanyNoticeCount(String userId);
    /**
     * 查询任务数
     * userId 客户公司的编号
     * @return
     */
    Integer getComOrderNum(String userId);
    
    /**
     * 查询已提交的统记订单数
     * userId 客户公司的编号
     * @return 处理中的订单总数
     */
    Integer  getTotalCountByYtj(String userId,Integer orderState);
    /**
     * 查询已完成的订单条数
     * userId 客户公司的编号
     * @return 已完成的订单总数
     */
    Integer getTotalCountByYwc(String userId,Integer orderState);
    /**
     * 查询已分配的订单条数
     * @param userId
     * @param orderState
     * @return
     */
    Integer getTotalCountByYfp(String userId,Integer orderState);
    
    /**
     * 查询按照本日汇总订单数 
     * userId 客户公司的编号
     * @return 按照本日汇总订单数
     */
    List<Object[]> getNumByDay(String userId);
    /**
     * 查询按照本周汇总订单数
     * userId 客户公司的编号
     * @return
     */
    List<Object []> getOrderNumberByWeek(String userId);
    /**
     * 查询按照本月来汇总订单数
     * userId 客户公司的编号
     * @return 按照本月来汇总订单数
     */
    List<Object[]> getOrderNumberByMonth(String userId);
    /**
     * 查询按照本季度汇总订单数
     * @return 按照本季度汇总订单数
     */
    List<Object[]> getOrderNumberByQua(String userId);
    /**
     * 查询统计各类型的单据在总任务订单数所占的比重
     * @return 统计各类型的单据在总任务订单数所占的比重
     */
    List<Object[]> getBillProportion(String userId);
    
    
    /**
     * 查询供应商的公告通知信息
     * @return 公告通知消息
     */
    List<NoticeInformed> getSupNoticeInformed();
    /**
     * 查询供应商的站内消息信息
     * @return 站内消息
     */
    List<Notice>  getSupNotice(String userId);
    
    /**
     * 查询分配给供应商的任务数
     * userId 客户公司的编号
     * @return
     */
    Integer getSupOrderNum(String userId,Integer orderState);
    
    /**
     * 查询分配给供应商的已提交的统计订单数
     * userId 客户公司的编号
     * @return 处理中的订单总数
     */
    Integer  getSupTotalCountByYtj(String userId,Integer orderState);
    /**
     * 查询分配给供应商的已分配的订单条数
     * userId 客户公司的编号
     * @return 已完成的订单总数
     */
    Integer getSupTotalCountByYfp(String userId,Integer orderState);
    /**
     * 查询分配给供应商的已完成的订单条数
     * @param userId
     * @param orderState
     * @return
     */
    Integer getSupTotalCountByYwc(String userId,Integer orderState);
    
    /**
     * 查询按照本日汇总分配给供应商的订单数 
     * userId 客户公司的编号
     * @return 按照本日汇总订单数
     */
    List<Object[]> getSupNumByDay(String userId);
    /**
     * 查询按照本周汇总分配给供应商的订单数
     * userId 客户公司的编号
     * @return
     */
    List<Object []> getSupOrderNumberByWeek(String userId);
    /**
     * 查询按照本月来汇总分配给供应商的订单数
     * userId 客户公司的编号
     * @return 按照本月来汇总订单数
     */
    List<Object[]> getSupOrderNumberByMonth(String userId);
    /**
     * 查询按照本季度汇总分配给供应商的订单数
     * @return 按照本季度汇总订单数
     */
    List<Object[]> getSupOrderNumberByQua(String userId);
    /**
     * 查询统计各类型的单据在分配给供应商的总任务订单数所占的比重
     * @return 统计各类型的单据在总任务订单数所占的比重
     */
    List<Object[]> getSupBillProportion(String userId);
    
    /**
     * 根据id得到客户公司信息
     * @param id
     * @return
     */
    CompanyInfo getCompanyInfo(String id);
}
