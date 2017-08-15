package com.pm.order.busin;
/**
 * 订单的业务接口
 * @author Administrator
 *
 */

import java.util.Date;
import java.util.List;

import com.pm.order.bean.BgEcExpDomain;
import com.pm.order.bean.BgIcExpDomain;
import com.pm.order.bean.CcEcExpDomain;
import com.pm.order.bean.CcIcExpDomain;
import com.pm.order.bean.DingdanAddtion;
import com.pm.order.bean.WlEcExpDomain;
import com.pm.order.bean.WlIcExpDomain;
import com.pm.order.bean.WmEcExpDomain;
import com.pm.order.bean.WmIcExpDomain;
import com.sup.order.bean.OrderFrom;

public interface OrderFromBusin {
    
    /**
     * 按类型保存任务
     * @param taskId
     */
    void saveTaskIdOrderFrom(String taskId);
    
    /**
     * 根据  任务Id得到一条任务信息
     * @param id
     * @return
     */
    OrderFrom getOrderFrom(String id);
    
    /**
     * @Description 根据任务ID返回订单列表
     * @author Chu Zhaocheng
     * @date 2017年6月15日 下午12:52:28
     * @action listOrderFrom
     * @return List<OrderFrom>
     */
    List<OrderFrom> listOrderFrom(String taskId);
    /**
     * 对订单进行修改操作
     * @param task
     */
    void saveOrderFrom(OrderFrom orderFrom,String suppersId,String flowTypeId,String taskId,String currUserId);
    
    /**
     * 根据类型Id和主任务Id得到一个子订单信息
     * @param typeId
     * @param taskId
     */
    OrderFrom getTypeIdByOrderFrom(String typeId, String taskId);
    
    /**
     * 客服对订单进行终止
     * @param id
     */
    void terminationOrderFrom(String id);
    
    /**
     * 根据订单类型得到订单的数量
     * @param id
     * @param flag -1:提前 0:按时 1: 延迟  2:同类型的全部订单
     * @return
     */
    Integer getOrderStatusCount(String id,String flag, String name,String supName, String startDate, String endDate);
    
    /**
     * 会员订单数
     * @param endDate 
     * @param startDate
     * @param endDate
     * @return
     */
    List<Integer> getOrderMemberNum(Date startDate, Date endDate);
    
    /**
     * 进口报关导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @param name 
     * @param supName 
     * @return 
     */
    List<BgIcExpDomain> getBgImccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name);
   
    /**
     * 出口报关导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @param name 
     * @param supName 
     * @return 
     */
    List<BgEcExpDomain> getBgEmccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name);
    /**
     * 物流出口导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @param name 
     * @param supName 
     * @return 
     */
    List<WlIcExpDomain> getWlImccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name);
    /**
     * 物流进口导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @param name 
     * @param supName 
     * @return 
     */
    List<WlEcExpDomain> getWlEmccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name);
   
    /**
     * 外贸进口导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @return 
     */
    List<WmIcExpDomain> getWmImccOrderExecl(String typeId,String orderCode, String startDate, String endDate);
   
    /**
     * 外贸出口导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @param name 
     * @param supName 
     * @return 
     */
    List<WmEcExpDomain> getWmEmccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name);
    
    /**
     * 仓储进库导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @param name 
     * @param supName 
     * @return 
     */
    List<CcIcExpDomain> getCcImccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name);
   
    /**
     * 仓储出库导出报表
     * @param orderCode
     * @param startDate
     * @param endDate
     * @param name 
     * @param supName 
     * @return 
     */
    List<CcEcExpDomain> getCcEmccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name);
   
    
    
    /**
     * 查询补充信息
     * @param id
     * @return
     */
    DingdanAddtion getOrderAddtion(String id);
   
}
