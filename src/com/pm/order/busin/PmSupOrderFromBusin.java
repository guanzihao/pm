package com.pm.order.busin;

import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.PurchaseOrderProduction;

/**
 * 订单的业务接口
 * @author Administrator
 *
 */
public interface PmSupOrderFromBusin {
    
    /**
     * 按类型保存任务
     * @param taskId
     */
    void saveOrderFrom(String task);
    void saveOrderFroms(PurchaseOrderProduction purchaseOrderProduction, String taskTypeId, String comwaimao);
    
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
}
