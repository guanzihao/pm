package com.sup.order.busin;

import com.sup.order.bean.CustomsDeAgreement;
import com.sup.order.bean.ExportCustomsDeAgreement;

public interface ExportCustomsDeAgreementBusin {

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
    void saveCustomsDeAgreement(ExportCustomsDeAgreement exportCustomsDeAgreement, String taskTypeId, String combaoguan,String orderFromId);
    
    /**
     * @Description 根据ID删除
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:48:47
     * @action deleteCustomsDeAgreement
     * @return void
     */
    void deleteCustomsDeAgreement(String id);

    ExportCustomsDeAgreement getObject(String id);
    /**
     * 修改数据保存
     * @param customsDeAgreement
     */
    void saveObject(ExportCustomsDeAgreement customsDeAgreement);

    
    
}
