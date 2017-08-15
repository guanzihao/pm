package com.pm.order.busin;

import java.util.List;

import com.sup.order.bean.ExContractDetail;
import com.sup.order.bean.ExportContract;

public interface PmExContractDetailBusin {

    /**
     * @Description 根据ID查询
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:23:12
     * @action getExContractDetail
     * @return ExContractDetail
     */
    List<ExContractDetail> getExContractDetail(String id);
    ExportContract getExportContract(String id);
    /**
     * @Description 保存操作
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:48:27
     * @action saveExContractDetail
     * @return void
     */
   void saveExportContract(ExportContract exportContract, String taskTypeId, String comwaimao, String commodityName, String models, String quantity, String unitPrice, String totalAmount);

}
