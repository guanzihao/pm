package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.BgFlowImccNode;
import com.sup.flow.bean.FlowNode;
import com.sup.flow.busin.BgFlowImccBusin;

@SuppressWarnings("unchecked")
@Service
public class BgFlowImccBusinImpl implements BgFlowImccBusin{

    @Autowired
    private BusinApi businApi;
    
    @Override
    public String getBgFlowImcc(String id) {
        String str="接单";
        BgFlowImcc bgFlowImcc =(BgFlowImcc) businApi.get(BgFlowImcc.class, id);
        String nodeId = bgFlowImcc.getNodeId();
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
    public BgFlowImccNode getFlowImccNode(String id) {
        return (BgFlowImccNode) businApi.get(BgFlowImccNode.class, id);
    }
    
    @Override
    public void saveFlowImccNode(BgFlowImccNode flowImccNode, String billId) {
        if (billId!=null) {
            BgFlowImcc flowImcc=(BgFlowImcc) businApi.get(BgFlowImcc.class, billId);
            flowImccNode.setTaskId(flowImcc.getTaskId());
            businApi.save(flowImccNode);
        }
    }

    @Override
    public void removeFlowImccNode(String id) {
         BgFlowImccNode bgFlowImccNode= (BgFlowImccNode) businApi.get(BgFlowImccNode.class, id);
        if (bgFlowImccNode != null) {
            bgFlowImccNode.setFlag(-1);
            businApi.save(bgFlowImccNode);
        }
        
    }
    /**
     * 获取供应商信息
     */
    public List<SupCompanyInfo> getSupplierList(){
        return (List<SupCompanyInfo>)businApi.getList(SupCompanyInfo.class);
        
    }
    /**
     * 根据id获取供应商信息
     */
    public  SupCompanyInfo getSupplierById(String id){
         String sql="from SupCompanyInfo a where a.comCode=?";
         return (SupCompanyInfo)businApi.getQueryObject(sql, new Object[]{id});
    }
    /**
     * 根据流程id查询流程节点
     */
    public List<FlowNode> getFlowNode(String id){
        String sql="from FlowNode a where a.flowId=?";
        return (List<FlowNode>)businApi.getQueryList(sql, new Object[]{id});
    }
    
    public BgFlowImcc getBgFlowImccInfo(String id){
        return (BgFlowImcc)businApi.get(BgFlowImcc.class,id);
    }
    
    public List<Object[]> getBgFlowImccNodeList(String id){
        String sql="SELECT imccNode.status,node.node_name,"
                  +" imccNode.bussiness_date,imccNode.inspection_date,imccNode.release_date"
                  +" FROM BG_Flow_IMCC_Node imccNode"
                  +" LEFT JOIN PM_FlowNode node ON imccNode.node_id = node.obj_Id"
                  +" where  imccNode.billid=?";
        return (List<Object[]>)businApi.getSQLQuery(sql, new Object[]{id}).list();
    }

}
