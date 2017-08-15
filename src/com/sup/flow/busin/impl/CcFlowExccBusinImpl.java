package com.sup.flow.busin.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.CcFlowExcc;
import com.sup.flow.bean.CcFlowExccNode;
import com.sup.flow.busin.CcFlowExccBusin;

@SuppressWarnings("unchecked")
@Service
public class CcFlowExccBusinImpl implements CcFlowExccBusin{

    @Autowired
    private BusinApi businApi;
    
    @Override
    public String getCcFlowExcc(String id) {
        String str="接单登记";
        CcFlowExcc ccFlowExcc =(CcFlowExcc) businApi.get(CcFlowExcc.class, id);
        String nodeId = ccFlowExcc.getNodeId();
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
    public CcFlowExccNode getCcFlowExccNode(String id) {
        return (CcFlowExccNode) businApi.get(CcFlowExccNode.class, id);
    }
    
    @Override
    public void saveCcFlowExccNode(CcFlowExccNode ccFlowExccNode, String billId) {
        if (billId!=null) {
            CcFlowExcc ccFlowExcc=(CcFlowExcc) businApi.get(CcFlowExcc.class, billId);
            ccFlowExccNode.setTaskId(ccFlowExcc.getTaskId());
            businApi.save(ccFlowExccNode);
        }
    }

    @Override
    public void removeCcFlowExccNode(String id) {
        CcFlowExccNode ccFlowExccNode=(CcFlowExccNode) businApi.get(CcFlowExccNode.class, id);
        if (ccFlowExccNode!=null) {
            ccFlowExccNode.setFlag(-1);
            businApi.save(ccFlowExccNode);
        }
    }

}
