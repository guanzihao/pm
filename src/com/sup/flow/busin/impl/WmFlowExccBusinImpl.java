package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.WmFlowExcc;
import com.sup.flow.bean.WmFlowExccNode;
import com.sup.flow.busin.WmFlowExccBusin;

@SuppressWarnings("unchecked")
@Service
public class WmFlowExccBusinImpl implements WmFlowExccBusin{


    @Autowired
    private BusinApi businApi;
    
    @Override
    public void saveWmFlowExccNode(WmFlowExccNode wmFlowExccNode, String billId, String nodeId) {
        if (billId!=null && nodeId!=null) {
            WmFlowExcc wmFlowExcc=(WmFlowExcc) businApi.get(WmFlowExcc.class, billId);
            wmFlowExccNode.setTaskId(wmFlowExcc.getTaskId());
            wmFlowExccNode.setNodeId(nodeId);
            businApi.save(wmFlowExccNode);
        }
    }

    @Override
    public WmFlowExccNode getWmFlowExccNode(String id) {
        return (WmFlowExccNode) businApi.get(WmFlowExccNode.class, id);
    }

    @Override
    public void removeWmFlowExccNode(String id) {
        WmFlowExccNode wmFlowExccNode = (WmFlowExccNode) businApi.get(WmFlowExccNode.class, id);
        if (wmFlowExccNode != null) {
            wmFlowExccNode.setFlag(-1);
            businApi.save(wmFlowExccNode);
        }
    }

    @Override
    public String getWmFlowExcc(String id) {
        String str="单证制作";
        WmFlowExcc wmFlowExcc =(WmFlowExcc) businApi.get(WmFlowExcc.class, id);
        String nodeId = wmFlowExcc.getNodeId();
        if (nodeId!=null&&!"".equals(nodeId)) {
            Query query = businApi.getQuery("SELECT a.nodeName FROM FlowNode a where a.id=?", new Object[] {nodeId });
            List<String> list = query.list();
            if (list!=null&&list.size()>0) {
                str=list.get(0);
            }
        }
        return str;
    }

}
