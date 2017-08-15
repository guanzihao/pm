package com.sup.flow.busin.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.CcFlowImcc;
import com.sup.flow.bean.CcFlowImccNode;
import com.sup.flow.busin.CcFlowImccBusin;

@SuppressWarnings("unchecked")
@Service
public class CcFlowImccBusinImpl implements CcFlowImccBusin{

    @Autowired
    private BusinApi businApi;
    
    @Override
    public String getCcFlowImcc(String id) {
        String str="接单登记";
        CcFlowImcc ccFlowImcc =(CcFlowImcc) businApi.get(CcFlowImcc.class, id);
        String nodeId = ccFlowImcc.getNodeId();
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
    public CcFlowImccNode getCcFlowImccNode(String id) {
        return (CcFlowImccNode) businApi.get(CcFlowImccNode.class, id);
    }
    
    @Override
    public void saveCcFlowImccNode(CcFlowImccNode ccFlowImccNode, String billId) {
        if (billId!=null) {
            CcFlowImcc ccFlowImcc=(CcFlowImcc) businApi.get(CcFlowImcc.class, billId);
            ccFlowImccNode.setTaskId(ccFlowImcc.getTaskId());
            businApi.save(ccFlowImccNode);
        }
    }

    @Override
    public void removeCcFlowImccNode(String id) {
        CcFlowImccNode ccFlowImccNode=(CcFlowImccNode) businApi.get(CcFlowImccNode.class, id);
        if (ccFlowImccNode!=null) {
            ccFlowImccNode.setFlag(-1);
            businApi.save(ccFlowImccNode);
        }
    }

}
