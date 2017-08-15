package com.sup.order.busin;

import java.util.List;

import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ExportShippingExCommission;
import com.sup.order.bean.OrderFrom;
import com.sup.order.bean.PurchaseOrderProduction;

/**
 * 订单的业务接口
 * @author Administrator
 *
 */
public interface SupOrderFromBusin {
    
    /**
     * 按类型保存任务
     * @param taskId
     */
    void saveOrderFrom(String task);
    void saveOrderFroms(PurchaseOrderProduction purchaseOrderProduction, String taskTypeId, String comwaimao, String detailNos, String descriptions, String units, String priceVilidityPeriods, String perPrices, String remarks, String orderFromId);
    
    /**
     * 保存子订单
     * @param taskId
     * @param taskTypeId
     * @param fusId 
     * @return
     */
    String saveOrderFrom(String taskId,String taskTypeId, String fusId);
    
    PurchaseOrderProduction infos(String id);
    
    ExportPurchaseOrderProduction info(String id);
    
    PurchaseOrderProduction getObject(String id);
    /**
     * 修改保存数据
     * @param purchaseOrderProduction
     */
    void saveObject(PurchaseOrderProduction purchaseOrderProduction);
     /**
     * 保存草稿后提交所有订单
     * @param taskId
     * @return
     */
    void submitOrderFrom(String taskId);
    /**
     * 修改保存详情数据
     * @param id
     * @param description
     * @param unit
     * @param priceVilidityPeriod
     * @param perPrice
     * @param remark
     */
    void saveObjectDetail(String fid,String[] id, String[] description, String[] unit, String[] priceVilidityPeriod, String[] perPrice, String[] remark);
    
    /**
     * 得到一个任务下的的所有订单
     * @param taskId
     * @return
     */
    List<OrderFrom> listOrderFrom(String taskId);
    
    OrderFrom getOrderFrom(String orderFromId);
    /**
     * 添加单据信息
     * @param typeId
     * @param orderFromId
     */
    void saveFlow(String typeId, String orderFromId);
    /**
     * 服务商接收订单
     * @param id
     */
    void saveSupReceiveOrder(String id);
    /**
     * 客户提交订单
     * @param id
     */
    void saveSubmitOrder(String id);
}
