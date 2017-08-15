package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.WmFlowImcc;
import com.sup.flow.bean.WmFlowImccNode;
import com.sup.flow.busin.WmFlowImccBusin;

@SuppressWarnings("unchecked")
@Service
public class WmFlowImccBusinImpl implements WmFlowImccBusin{


    @Autowired
    private BusinApi businApi;
    
    @Override
    public void saveWmFlowImccNode(WmFlowImccNode wmFlowImccNode, String billId, String nodeId) {
        if (billId!=null && nodeId!=null) {
            WmFlowImcc wmFlowImcc=(WmFlowImcc) businApi.get(WmFlowImcc.class, billId);
            wmFlowImccNode.setTaskId(wmFlowImcc.getTaskId());
            wmFlowImccNode.setNodeId(nodeId);
            businApi.save(wmFlowImccNode);
        }
    }

    @Override
    public WmFlowImccNode getWmFlowImccNode(String id) {
        return (WmFlowImccNode) businApi.get(WmFlowImccNode.class, id);
    }

    @Override
    public void removeWmFlowImccNode(String id) {
        WmFlowImccNode wmFlowImccNode = (WmFlowImccNode) businApi.get(WmFlowImccNode.class, id);
        if (wmFlowImccNode != null) {
            wmFlowImccNode.setFlag(-1);
            businApi.save(wmFlowImccNode);
        }
    }

    @Override
    public String getWmFlowImcc(String id) {
        String str="单证制作";
        WmFlowImcc wmFlowImcc =(WmFlowImcc) businApi.get(WmFlowImcc.class, id);
        String nodeId = wmFlowImcc.getNodeId();
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
