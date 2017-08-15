package com.pm.order.busin;

import com.sup.order.bean.CustomsDeAgreement;

public interface PmCustomsDeAgreementBusin {

    /**
     * @Description 根据ID查询
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:23:12
     * @action getCustomsDeAgreement
     * @return CustomsDeAgreement
     */
    CustomsDeAgreement getCustomsDeAgreement(String id);
    

    /**
     * @Description 根据ID删除
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:48:47
     * @action deleteCustomsDeAgreement
     * @return void
     */
    void deleteCustomsDeAgreement(String id);
    /**
     * 保存操作
     * @param customsDeAgreement
     * @param taskTypeId
     * @param comwaimao
     */
    void saveExportContract(CustomsDeAgreement customsDeAgreement, String taskTypeId, String comwaimao);
}
