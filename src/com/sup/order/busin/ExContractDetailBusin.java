package com.sup.order.busin;

import java.util.List;

import com.sup.order.bean.ExContractDetail;
import com.sup.order.bean.ExportContract;

public interface ExContractDetailBusin {

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
   void saveExportContract(ExportContract exportContract, String taskTypeId, String comwaimao,String commodityNameId, String commodityName, String models, String quantity, String unitPrice, String totalAmount,String orderFromId);
    
   ExportContract getObject(String exId);
   /**
    * 修改保存数据
    * @param exportContract
    */
void saveObject(ExportContract exportContract);
/**
 * 修改或保存数据
 * @param id
 * @param ids
 * @param commodityName
 * @param models
 * @param quantity
 * @param unitPrice
 * @param totalAmount
 */
void saveObjectDetail(String id, String[] ids, String[] commodityName, String[] models, String[] quantity, String[] unitPrice, String[] totalAmount,String commodityId[]);
}
