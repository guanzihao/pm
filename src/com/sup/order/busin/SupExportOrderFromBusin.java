package com.sup.order.busin;

import com.sup.order.bean.ExportPurchaseOrderProduction;
import com.sup.order.bean.ExportShippingExCommission;

/**
 * 订单的业务接口
 * @author Administrator
 *
 */
public interface SupExportOrderFromBusin {
    
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
    void saveOrderFroms(ExportPurchaseOrderProduction exportPurchaseOrderProduction, String taskTypeId, String comwaimao, String detailNos, String descriptions, String units, String priceVilidityPeriods, String perPrices, String remarks,String orderFromId);
    
    ExportPurchaseOrderProduction getinfo(String id);

    ExportPurchaseOrderProduction getObject(String id);
    /**
     * 修改保存数据
     * @param exportShippingExCommission
     */
    void saveObject(ExportPurchaseOrderProduction exportShippingExCommission);
    /**
     * 保存修改明细信息
     * @param id
     * @param ids
     * @param description
     * @param unit
     * @param priceVilidityPeriod
     * @param perPrice
     * @param remark
     */
    void saveObjectDetail(String id, String[] ids, String[] description, String[] unit, String[] priceVilidityPeriod, String[] perPrice, String[] remark);
}
