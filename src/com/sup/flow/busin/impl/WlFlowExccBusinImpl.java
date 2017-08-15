package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.WlFlowExcc;
import com.sup.flow.bean.WlFlowExccNode;
import com.sup.flow.busin.WlFlowExccBusin;

/**
 * @Description: 物流出口模块业务处理实现
 * @author Chu Zhaocheng
 * @date 2017年6月20日 下午2:57:09
 */
@Service
public class WlFlowExccBusinImpl implements WlFlowExccBusin {

    @Autowired
    private BusinApi businApi;
    
    @SuppressWarnings("unchecked")
    @Override
    public String getWlFlowExcc(String id) {
        String str="接单";
        WlFlowExcc wlFlowExcc =(WlFlowExcc) businApi.get(WlFlowExcc.class, id);
        String nodeId = wlFlowExcc.getNodeId();
        if (nodeId != null && !"".equals(nodeId)) {
            Query query = businApi.getQuery("SELECT a.nodeName FROM FlowNode a where a.id = ?", new Object[] {nodeId });
            List<String> list = query.list();
            if (list != null && list.size() >0 ) {
                str=list.get(0);
            }
         }
        return str;
    }
    
    public WlFlowExccNode getWlFlowExccNode(String id){
         return (WlFlowExccNode)businApi.get(WlFlowExccNode.class, id);
    }
    
    public void saveWlFlowExccNode(WlFlowExccNode wlflowExccNode, String billId,String nodeId){
        if (billId!=null && nodeId!=null) {
            WlFlowExcc exccImcc=(WlFlowExcc) businApi.get(WlFlowExcc.class, billId);
            wlflowExccNode.setTaskId(exccImcc.getTaskId());
            wlflowExccNode.setNodeId(nodeId);
            businApi.save(wlflowExccNode);
        }
    }

   public void removeWlFlowExccNode(String id){
       WlFlowExccNode wlFlowExccNode=(WlFlowExccNode) businApi.get(WlFlowExccNode.class, id);
       if (wlFlowExccNode!=null) {
           wlFlowExccNode.setFlag(-1);
           businApi.save(wlFlowExccNode);
       }
   }

}
