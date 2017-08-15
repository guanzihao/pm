package com.sup.order.busin;

import com.sup.order.bean.ShippingExCommission;

/**
 *  空海运出口委托书接口
 * @author Administrator
 *
 */
public interface ShippingExCommissionBusin {
    
    /**
     * 根据Id得到一条空海运出口委托书信息
     * @param id
     * @return
     */
    ShippingExCommission getShippingExCommission(String id);
    
    /**
     * 保存空海运出口委托书
     * @param exCommission
     */
    void saveShippingExCommission(ShippingExCommission shippingExCommission, String taskTypeId, String comwaimao,String orderFromId);
    
    /**
     * 空海运出口委托书
     */
    void  removeShippingExCommission(String id);

    ShippingExCommission getObject(String id);
    /**
     * 修改数据保存
     * @param shippingExCommission
     */
    void saveObject(ShippingExCommission shippingExCommission);

    
    
    
}
