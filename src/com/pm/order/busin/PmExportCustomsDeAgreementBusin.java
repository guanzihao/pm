package com.pm.order.busin;

import com.sup.order.bean.ExportCustomsDeAgreement;

public interface PmExportCustomsDeAgreementBusin {

    /**
     * @Description 根据ID查询
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:23:12
     * @action getCustomsDeAgreement
     * @return CustomsDeAgreement
     */
    ExportCustomsDeAgreement getCustomsDeAgreement(String id);
    
    /**
     * @Description 保存操作
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:48:27
     * @action saveCustomsDeAgreement
     * @return void
     */
    void saveCustomsDeAgreement(ExportCustomsDeAgreement exportCustomsDeAgreement, String taskTypeId, String combaoguan);
    
    /**
     * @Description 根据ID删除
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:48:47
     * @action deleteCustomsDeAgreement
     * @return void
     */
    void deleteCustomsDeAgreement(String id);

    
    
}
