package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.WlFlowTransport;
import com.sup.flow.bean.WlFlowTransportNode;
import com.sup.flow.busin.WlFlowTransportBusin;
@SuppressWarnings("unchecked")
@Service
public class WlFlowTransportBusinImpl implements WlFlowTransportBusin{

    @Autowired
    private BusinApi businApi;
    
    @Override
    public String getWlFlowTransport(String id) {
        String str="接单";
        WlFlowTransport wlFlowTransport =(WlFlowTransport) businApi.get(WlFlowTransport.class, id);
        String nodeId = wlFlowTransport.getNodeId();
        if (nodeId!=null&&!"".equals(nodeId)) {
            Query query = businApi.getQuery("SELECT  a.nodeName FROM FlowNode a where a.id=?", new Object[] {nodeId });
            List<String> list = query.list();
            if (list!=null&&list.size()>0) {
                str=list.get(0);
            }
        }
        return str;
    }

    @Override
    public WlFlowTransportNode getWlFlowTransportNode(String id) {
        return (WlFlowTransportNode) businApi.get(WlFlowTransportNode.class, id);
    }
    
    @Override
    public void saveWlFlowTransportNode(WlFlowTransportNode wlFlowTransportNode, String billId) {
        if (billId!=null) {
            WlFlowTransport wlFlowTransport=(WlFlowTransport) businApi.get(WlFlowTransport.class, billId);
            wlFlowTransportNode.setTaskId(wlFlowTransport.getTaskId());
            businApi.save(wlFlowTransportNode);
        }
    }

    @Override
    public void removeWlFlowTransportNode(String id) {
        WlFlowTransportNode wlFlowTransportNode=(WlFlowTransportNode) businApi.get(WlFlowTransportNode.class, id);
        if (wlFlowTransportNode!=null) {
            wlFlowTransportNode.setFlag(-1);
            businApi.save(wlFlowTransportNode);
        }
    }

}
