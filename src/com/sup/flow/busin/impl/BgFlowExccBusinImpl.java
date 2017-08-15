package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.busin.impl.RunJdbc;
import com.sup.flow.bean.BgFlowExcc;
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.BgFlowExccNode;
import com.sup.flow.bean.BgFlowImccNode;
import com.sup.flow.bean.FlowNode;
import com.sup.flow.busin.BgFlowExccBusin;
@SuppressWarnings("unchecked")
@Service
public class BgFlowExccBusinImpl implements BgFlowExccBusin{

    @Autowired
    private BusinApi businApi;
    
    @Override
    public String getBgFlowExcc(String id) {
        String str="接单";
        BgFlowExcc bgFlowExcc =(BgFlowExcc) businApi.get(BgFlowExcc.class, id);
        String nodeId="";
        if(bgFlowExcc!=null){
            nodeId = bgFlowExcc.getNodeId();
        }
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
    public BgFlowExccNode getFlowExccNode(String id) {
        
        return (BgFlowExccNode) businApi.get(BgFlowExccNode.class, id);
    }
    
    @Override
    public void saveFlowExccNode(BgFlowExccNode flowExccNode, String billId) {
        if (billId!=null) {
            BgFlowExcc flowExcc=(BgFlowExcc) businApi.get(BgFlowExcc.class, billId);
            flowExccNode.setTaskId(flowExcc.getTaskId());
            businApi.save(flowExccNode);
        }
    }

    @Override
    public void removeFlowExccNode(String id) {
        BgFlowExccNode flowExccNode=(BgFlowExccNode) businApi.get(BgFlowExccNode.class, id);
        if (flowExccNode!=null) {
            flowExccNode.setFlag(-1);
            businApi.save(flowExccNode);
        }
        
    }
    /**
     * 根据id获取供应商信息
     */
    public  SupCompanyInfo getSupplierById(String id){
         String sql="from SupCompanyInfo a where a.comCode=?";
         return (SupCompanyInfo)businApi.getQueryObject(sql, new Object[]{id});
    }
    
    /**
     * 获取供应商信息
     */
    public List<SupCompanyInfo> getSupplierList(){
        return (List<SupCompanyInfo>)businApi.getList(SupCompanyInfo.class);
        
    }
    
    /**
     * 根据票据id查询报关出口票据信息
     * @param id
     * @return
     */
    public BgFlowExcc getBgFlowExccInfo(String id){
        return (BgFlowExcc) businApi.get(BgFlowExcc.class, id);
    }

    @Override
    public BgFlowExcc getOrderCc(String orderid) {
       
        return (BgFlowExcc) businApi.getQueryObject("from BgFlowExcc e where inner join  e.orderFrom c where c.id = ?",new Object[]{});
    }

    @Override
    public void updateorderstate(String danjuType,String orderId) {
        String type="";
        switch (danjuType) {
        case "bgex":
            type="pro_FlowNodeBgExccState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        case "bgim":
            type="pro_FlowNodeBgImccState(?)";
            RunJdbc.runchunchu(type,orderId);      
            break;
        case "ccex":
            type="pro_FlowNodeCcExccState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        case "ccim":
            type="pro_FlowNodeCcImccState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        case "wlim":
            type="pro_FlowNodeWLImccState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        case "wlex":
            type="pro_FlowNodeWLExccState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        case "wltrop":
            type="pro_FlowNodeWLTropState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        case "wmex":
            type="pro_FlowNodeWmExccState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        case "wmim":
            type="pro_FlowNodeWmIMccState(?)";
            RunJdbc.runchunchu(type,orderId);
            break;
        }
    }

    @Override
    public FlowNode getFlowNode(String id) {
        return (FlowNode)businApi.get(FlowNode.class, id);
    }

}
