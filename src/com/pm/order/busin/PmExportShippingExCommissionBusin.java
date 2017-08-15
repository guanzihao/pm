package com.pm.order.busin;

import com.sup.order.bean.ExportShippingExCommission;

/**
 *  空海运出口委托书接口
 * @author Administrator
 *
 */
public interface PmExportShippingExCommissionBusin {
    
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
    void saveShippingExCommission(ExportShippingExCommission shippingExCommission, String taskTypeId, String combaoguan);
    
    /**
     * 空海运出口委托书
     */
    void  removeShippingExCommission(String id);

   
    
    
}
