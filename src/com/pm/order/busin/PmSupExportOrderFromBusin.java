package com.pm.order.busin;

import com.sup.order.bean.ExportPurchaseOrderProduction;

/**
 * 订单的业务接口
 * @author Administrator
 *
 */
public interface PmSupExportOrderFromBusin {
    
    /**
     * 按类型保存任务
     * @param taskId
     */
    void saveOrderFrom(String task);
    
    /**
     * 保存子订单
     * @param taskId
     * @param taskTypeId
     * @param fusId 
     * @return
     */
    String saveOrderFrom(String taskId,String taskTypeId, String fusId);
    void saveOrderFroms(ExportPurchaseOrderProduction exportPurchaseOrderProduction, String taskTypeId, String comwaimao);
    
    ExportPurchaseOrderProduction getinfo(String id);
}
