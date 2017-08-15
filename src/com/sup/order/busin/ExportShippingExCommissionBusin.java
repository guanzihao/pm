package com.sup.order.busin;

import com.sup.order.bean.ExportShippingExCommission;

/**
 *  空海运出口委托书接口
 * @author Administrator
 *
 */
public interface ExportShippingExCommissionBusin {
    
    /**
     * 根据Id得到一条空海运出口委托书信息
     * @param id
     * @return
     */
    ExportShippingExCommission getShippingExCommission(String id);
    
    /**
     * 保存空海运出口委托书
     * @param exCommission
     */
    void saveShippingExCommission(ExportShippingExCommission shippingExCommission, String taskTypeId, String combaoguan,String orderFromId);
    
    /**
     * 空海运出口委托书
     */
    void  removeShippingExCommission(String id);
    
    /**
     * 得到一个对象
     * @param id
     * @return
     */
    ExportShippingExCommission getObject(String id);
    
    /**
     * 修改数据保存
     * @param exportShippingExCommission
     */
    void saveObject(ExportShippingExCommission exportShippingExCommission);

   
    
    
}
