package com.pm.order.busin;

import com.sup.order.bean.TropShippingExCommission;

/**
 *  空海运运输委托书接口
 * @author Administrator
 *
 */
public interface PmTropShippingExCommissionBusin {
    
    /**
     * 根据Id得到一条空海运出口委托书信息
     * @param id
     * @return
     */
    TropShippingExCommission getShippingExCommission(String id);
    
    
    /**
     * 空海运出口委托书
     */
    void  removeShippingExCommission(String id);

    void saveShippingExCommission(TropShippingExCommission shippingExCommission, String taskTypeId, String comwaimao);
    
    
}
