package com.sup.order.busin;

import java.util.List;

import com.sup.order.bean.ImContractDetail;
import com.sup.order.bean.ImportContract;

/**
 * @Description: 进口合同业务接口
 * @author Chu Zhaocheng
 * @date 2017年6月26日 上午11:21:47
 */
public interface ImportContractBusin {

    /**
     * @Description 根据ID查询
     * @author Chu Zhaocheng
     * @date 2017年6月26日 上午11:23:12
     * @action getImportContract
     * @return ImportContract
     */
    ImportContract getImportContract(String id);
    
    List<ImContractDetail> getImContractDetail(String id);
    /**
     * 保存合同明细
     * @param commodityName
     * @param model
     * @param quantity
     * @param unitPrice
     * @param totalAmount
     */

    void saveImContract(ImportContract importContract, String taskTypeId, String comwaimao,String commodityNameId, String commodityName, String models, String quantity, String unitPrice, String totalAmount,String orderFromId);

    ImportContract getObject(String icId);
    /**
     * 修改保存数据
     * @param importContract
     */
    void saveObject(ImportContract importContract);
    /**
     * 修改或保存数据
     * @param ids
     * @param commodityName
     * @param models
     * @param quantity
     * @param unitPrice
     * @param totalAmount
     */
    void saveObjectDetail(String fid,String[] ids, String[] commodityName, String[] models, String[] quantity, String[] unitPrice, String[] totalAmount,String commodityId[]);
}
