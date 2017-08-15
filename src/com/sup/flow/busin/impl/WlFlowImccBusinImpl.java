package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.WlFlowImcc;
import com.sup.flow.bean.WlFlowImccNode;
import com.sup.flow.busin.WlFlowImccBusin;

@SuppressWarnings("unchecked")
@Service
public class WlFlowImccBusinImpl implements WlFlowImccBusin {

    @Autowired
    private BusinApi businApi; 
    
    @Override
    public WlFlowImccNode getWlFlowImccNode(String id) {
        return (WlFlowImccNode) businApi.get(WlFlowImccNode.class, id);
    }

    @Override
    public void removeWlFlowImccNode(String id) {
        WlFlowImccNode wlFlowImccNode = (WlFlowImccNode) businApi.get(WlFlowImccNode.class, id);
        if (wlFlowImccNode != null) {
            wlFlowImccNode.setFlag(-1);
            businApi.save(wlFlowImccNode);
        }
    }

    @Override
    public void saveWlFlowImccNode(WlFlowImccNode wlFlowImccNode, String billId, String nodeId) {
        if (billId != null && nodeId != null) {
            WlFlowImcc wlFlowImcc = (WlFlowImcc) businApi.get(WlFlowImcc.class, billId);
            wlFlowImccNode.setTaskId(wlFlowImcc.getId());
            wlFlowImccNode.setNodeId(nodeId);
            businApi.save(wlFlowImccNode);
        }
    }

    @Override
    public String getWlFlowImcc(String id) {
        String str="接单";
        WlFlowImcc wlFlowImcc =(WlFlowImcc) businApi.get(WlFlowImcc.class, id);
        String nodeId = wlFlowImcc.getNodeId();
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
