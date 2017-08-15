package com.pm.order.busin.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.core.model.PageBean;
import com.pm.core.model.TaskState;
import com.pm.core.util.DateUtil;
import com.pm.core.util.SqlserverConnUtil;
import com.pm.core.util.StringUtil;
import com.pm.order.bean.BgEcExpDomain;
import com.pm.order.bean.BgIcExpDomain;
import com.pm.order.bean.CcEcExpDomain;
import com.pm.order.bean.CcIcExpDomain;
import com.pm.order.bean.DingdanAddtion;
import com.pm.order.bean.WlEcExpDomain;
import com.pm.order.bean.WlIcExpDomain;
import com.pm.order.bean.WmEcExpDomain;
import com.pm.order.bean.WmIcExpDomain;
import com.pm.order.busin.OrderFromBusin;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.pm.task.bean.Task;
import com.sup.flow.bean.BgFlowExcc;
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.CcFlowExcc;
import com.sup.flow.bean.CcFlowImcc;
import com.sup.flow.bean.FlowType;
import com.sup.flow.bean.WlFlowExcc;
import com.sup.flow.bean.WlFlowImcc;
import com.sup.flow.bean.WlFlowTransport;
import com.sup.flow.bean.WmFlowExcc;
import com.sup.flow.bean.WmFlowImcc;
import com.sup.order.bean.OrderFrom;
@SuppressWarnings("unchecked")
@Service
public class OrderFromBusinImpl implements OrderFromBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public void saveTaskIdOrderFrom(String taskId) {
        Task task = (Task) businApi.get(Task.class, taskId);

        if (task != null) {
            String oldAllId = task.getTackType();
            String[] newAllId = oldAllId.split(",");
            for (String string : newAllId) {
                OrderFrom orderFrom = new OrderFrom();
                orderFrom.setTask(task);
                FlowType flowType = new FlowType();
                flowType.setId(string);
                orderFrom.setFlowType(flowType);
                orderFrom.setOrderCheck(0);
                businApi.save(orderFrom);
            }
        }
    }

    @Override
    public List<OrderFrom> listOrderFrom(String taskId) {
        return businApi.getQueryList("from OrderFrom a where a.task.id = ? ", new Object[] { taskId });
    }

    @Override
    public OrderFrom getOrderFrom(String id) {
        return (OrderFrom) businApi.get(OrderFrom.class, id);
    }

    @Override
    public void saveOrderFrom(OrderFrom orderFrom, String suppersId, String flowTypeId, String taskId, String currUserId) {
        if (orderFrom != null) {
            SupCompanyInfo companyInfo = new SupCompanyInfo();
            companyInfo.setId(suppersId);
                orderFrom.setSuppers(companyInfo);
                orderFrom.setOrderCheckDate(new Date(System.currentTimeMillis()));
                orderFrom.setOrderAuditor(currUserId);
                orderFrom.setOrderState(TaskState.YTJ);
            businApi.save(orderFrom);
        }

    }

    @Override
    public OrderFrom getTypeIdByOrderFrom(String typeId, String taskId) {
       
      return  (OrderFrom) businApi.getQueryListObject("FROM OrderFrom a where a.flowType=? and a.task.id=?", new Object[]{typeId,taskId}); 
    }

    @Override
    public void terminationOrderFrom(String id) {
        OrderFrom from = (OrderFrom) businApi.get(OrderFrom.class, id);
        if (from!=null) {
            from.setOrderState(TaskState.YZZ);
            businApi.save(from);
        }
    }

    @Override
    public Integer getOrderStatusCount(String id,String flag,String name,String supName,String startDate,String endDate) {
        //flag -1:提前 0:按时 1: 延迟 2:同类型的全部订单
        StringBuffer sql=new StringBuffer();
        switch (flag) {
        case "-1":
            sql.append("SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE o.order_state!='5' AND o.accomplish_date IS NOT NULL AND convert(varchar(10),accomplish_date,121)<convert(varchar(10),project_accomplish_date,121) AND tack_type_id=?");
            break;

        case "0":
            sql.append("SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE o.order_state!='5' AND o.accomplish_date IS NOT NULL AND convert(varchar(10),accomplish_date,121)=convert(varchar(10),project_accomplish_date,121) AND tack_type_id=?");
            break;
        case "1":
            sql.append("SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE o.order_state!='5' AND o.accomplish_date IS NOT NULL AND convert(varchar(10),accomplish_date,121)>convert(varchar(10),project_accomplish_date,121) AND tack_type_id=?");
    
            break;
        case "2":
            sql.append("SELECT COUNT(1) FROM PM_OrderFrom o INNER JOIN PM_FlowType ft ON o.tack_type_id=ft.obj_Id INNER JOIN PM_Task t ON o.task_id=t.obj_Id INNER JOIN PM_COMPANY_CompanyInfo ci ON  t.user_id=ci.obj_Id LEFT JOIN PM_COMPANY_SupCompanyInfo suci ON o.supplier=suci.obj_Id  WHERE o.order_state!='5' AND o.accomplish_date IS NOT NULL AND tack_type_id=?");
            
            break;
        }
        List<Object> list=new ArrayList<Object>();
        list.add(id);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if (name!=null&&!"".equals(name)) {
            sql.append(" AND ci.com_Name=?" );
            list.add(name);
        }
        if (supName!=null&&!"".equals(supName)) {
            sql.append(" AND suci.comName=? ");
            list.add(supName);
        }
        if (startDate!=null&&!"".equals(startDate)) {
            try {
                Date date=sdf.parse(startDate);
                sql.append(" AND o.obj_createDate >=?");
                list.add(date);
            } catch (ParseException e) {
            }
           
        }
        if (endDate!=null&&!"".equals(endDate)) {
            try {
                Date date=sdf.parse(endDate);
                sql.append(" AND o.obj_createDate<=?");
                list.add(date);
            } catch (ParseException e) {
            }
        }
        return (Integer) businApi.getSQLQuery(sql.toString(),list.toArray() ).uniqueResult();
    }

    @SuppressWarnings("unused")
    @Override
    public List<Integer> getOrderMemberNum(Date startDate, Date endDate) {
        List<Integer> list = new ArrayList<Integer>();
        
        Connection con = null;
        ResultSet rs = null;
        CallableStatement proc =null;
        try {
             con = SqlserverConnUtil.getConnection();
             proc = con.prepareCall("{call pro_orderMemberNum(?,?)}");
            proc.setDate(1, new java.sql.Date(startDate.getTime()));
            proc.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = proc.executeQuery();
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCount = rsMeta.getColumnCount();
            while (rs != null && rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
        }finally {
            if (proc!=null) {
                try {
                    proc.close();
                } catch (SQLException e) {
                }
            }
            SqlserverConnUtil.closeConnect(rs, null, con); 
        }
    return list;
    }
    
    
    @SuppressWarnings("rawtypes")
    @Override
    public List<BgIcExpDomain> getBgImccOrderExecl(String typeId,String orderCode, String startDate, String endDate, String supName, String name) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<BgIcExpDomain> listContents = new ArrayList<BgIcExpDomain>();
        try {
            
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bi.company_name,o.order_code,bi.task_type,bi.custom_no,bi.waybill_no,bi.flight,bi.item_name,bi.items,bi.gross,bi.bussiness_date,bi.inspection_date,bi.release_date,o.project_accomplish_date "
                    + "From PM_OrderFrom o INNER JOIN BG_Flow_IMCC bi on o.obj_Id=bi.order_from_id "
                    + " WHERE o.order_state!=5 AND o.tack_type_id=?  ");
            
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();
            while (rs.next()) {
                BgIcExpDomain wachatExpDomain=new BgIcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setCustomNo(rs.getString(4));
                wachatExpDomain.setWaybillNo(rs.getString(5));
                wachatExpDomain.setFlight(rs.getString(6));;
                wachatExpDomain.setItemName(rs.getString(7));
                wachatExpDomain.setItems(rs.getString(8)+"");
                wachatExpDomain.setGross(rs.getString(9)+"");
                wachatExpDomain.setBussinessDate(DateUtil.getStringDate(rs.getDate(10), "yyyy-MM-dd"));
                wachatExpDomain.setInspectionDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setReleaseDate(DateUtil.getStringDate(rs.getDate(12), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(13), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
            
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<BgEcExpDomain> getBgEmccOrderExecl(String typeId, String orderCode, String startDate, String endDate, String supName, String name) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<BgEcExpDomain> listContents = new ArrayList<BgEcExpDomain>();
        try {
            
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bin.company_name,o.order_code,bin.task_type,bin.custom_no,bin.waybill_no,bin.flight,bin.item_name,bin.items,bin.gross,bin.bussiness_date,bin.inspection_date,bin.release_date,o.project_accomplish_date "
                    + " from PM_OrderFrom o INNER JOIN BG_Flow_EMCC bin on o.obj_Id=bin.order_from_id "
                    + "WHERE o.order_state!=5 AND o.tack_type_id=? ");
            
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();  
            while (rs.next()) {
                BgEcExpDomain wachatExpDomain=new BgEcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setCustomNo(rs.getString(4));
                wachatExpDomain.setWaybillNo(rs.getString(5));
                wachatExpDomain.setFlight(rs.getString(6));;
                wachatExpDomain.setItemName(rs.getString(7));
                wachatExpDomain.setItems(rs.getString(8)+"");
                wachatExpDomain.setGross(rs.getString(9)+"");
                wachatExpDomain.setBussinessDate(DateUtil.getStringDate(rs.getDate(10), "yyyy-MM-dd"));
                wachatExpDomain.setInspectionDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setReleaseDate(DateUtil.getStringDate(rs.getDate(12), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(13), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
            
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @Override
    public List<WlEcExpDomain> getWlEmccOrderExecl(String typeId, String orderCode, String startDate, String endDate, String supName, String name) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<WlEcExpDomain> listContents = new ArrayList<WlEcExpDomain>();
        try { 
            
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bi.company_name,o.order_code,bi.task_type,bi.items,bi.gross,bi.volume,bi.pickup_addr,bi.order_receiving_date,bi.release_date,bi.shipping_date,o.project_accomplish_date  From PM_OrderFrom o INNER JOIN WL_Flow_EXCC bi on o.obj_Id=bi.order_from_id   WHERE o.order_state!=5 AND o.tack_type_id=?");
            
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();  
            while (rs.next()) {
                WlEcExpDomain wachatExpDomain=new WlEcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setItemName(rs.getString(4));
                wachatExpDomain.setItems(rs.getString(5));
                wachatExpDomain.setGross(rs.getString(6));
                wachatExpDomain.setVolume(rs.getString(7));
                wachatExpDomain.setPickupAddr(rs.getString(8));
                //wachatExpDomain.setAcceptDate(DateUtil.getStringDate(rs.getDate(9), "yyyy-MM-dd"));
                wachatExpDomain.setReleaseDate(DateUtil.getStringDate(rs.getDate(10), "yyyy-MM-dd"));
               // wachatExpDomain.setShipmentDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<WlIcExpDomain> getWlImccOrderExecl(String typeId, String orderCode, String startDate, String endDate, String supName, String name) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<WlIcExpDomain> listContents = new ArrayList<WlIcExpDomain>();
        try { 
            
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bi.company_name,o.order_code,bi.task_type,bi.item_name,bi.items,bi.gross,bi.volume,bi.delivery_addr,bi.accept_date,bi.switch_bill_date,bi.release_date,bi.shipment_date,o.project_accomplish_date  "
                    + "From PM_OrderFrom o INNER JOIN WL_Flow_IMCC bi on o.obj_Id=bi.order_from_id "
                    + "WHERE o.order_state!=5 AND o.tack_type_id=?");
            
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();  
            while (rs.next()) {
                WlIcExpDomain wachatExpDomain=new WlIcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setItemName(rs.getString(4));
                wachatExpDomain.setItems(rs.getString(5));
                wachatExpDomain.setGross(rs.getString(6));
                wachatExpDomain.setVolume(rs.getString(7));
                wachatExpDomain.setDeliveryAddr(rs.getString(8));
                wachatExpDomain.setAcceptDate(DateUtil.getStringDate(rs.getDate(9), "yyyy-MM-dd"));
                wachatExpDomain.setSwitchBillDate(DateUtil.getStringDate(rs.getDate(10), "yyyy-MM-dd"));
                wachatExpDomain.setReleaseDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setShipmentDate(DateUtil.getStringDate(rs.getDate(12), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(13), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<WmIcExpDomain> getWmImccOrderExecl(String typeId, String orderCode, String startDate, String endDate) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<WmIcExpDomain> listContents = new ArrayList<WmIcExpDomain>();
        try { 
            
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bi.company_name,o.order_code,bi.task_type,bi.item_name,bi.contract_num,bi.contract_currency,bi.contract_amount,bi.contract_start_date,bi.lc_start_date,bi.receive_date,bi.way_bill_date,bi.custom_clear_date,bi.pay_time,o.project_accomplish_date ");
            sql.append("From PM_OrderFrom o INNER JOIN WM_Flow_IMCC bi on o.obj_Id=bi.order_from_id   ");
            sql.append("WHERE o.order_state!=5 AND o.tack_type_id=? ");
            
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();  
            while (rs.next()) {
                WmIcExpDomain wachatExpDomain=new WmIcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setItemName(rs.getString(4));
                wachatExpDomain.setContractNum(rs.getString(5));
                wachatExpDomain.setContractCurrency(rs.getString(6));
                wachatExpDomain.setContractAmount(rs.getString(7));
                wachatExpDomain.setContractStartDate(DateUtil.getStringDate(rs.getDate(8), "yyyy-MM-dd"));
                wachatExpDomain.setLcStartDate(DateUtil.getStringDate(rs.getDate(9), "yyyy-MM-dd"));
                wachatExpDomain.setReceiveDate(DateUtil.getStringDate(rs.getDate(10), "yyyy-MM-dd"));
                wachatExpDomain.setWayBillDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setCustomClearDate(DateUtil.getStringDate(rs.getDate(12), "yyyy-MM-dd"));
                wachatExpDomain.setPayTime(DateUtil.getStringDate(rs.getDate(13), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(14), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<WmEcExpDomain> getWmEmccOrderExecl(String typeId, String orderCode, String startDate, String endDate, String supName, String name) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<WmEcExpDomain> listContents = new ArrayList<WmEcExpDomain>();
        try { 
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bi.company_name,o.order_code,bi.task_type,bi.item_name,bi.contract_num,bi.contract_currency,bi.contract_amount,bi.contract_start_date,bi.lc_start_date,bi.receive_date,bi.way_bill_date,bi.custom_clear_date,bi.pay_time,o.project_accomplish_date ");
            sql.append("From PM_OrderFrom o INNER JOIN WM_Flow_IMCC bi on o.obj_Id=bi.order_from_id   ");
            sql.append("WHERE o.order_state!=5 AND o.tack_type_id=?");
            
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();  
            while (rs.next()) {
                WmEcExpDomain wachatExpDomain=new WmEcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setItemName(rs.getString(4));
                wachatExpDomain.setContractNum(rs.getString(5));
                wachatExpDomain.setContractCurrency(rs.getString(6));
                wachatExpDomain.setContractAmount(rs.getString(7));
                wachatExpDomain.setContractStartDate(DateUtil.getStringDate(rs.getDate(8), "yyyy-MM-dd"));
                wachatExpDomain.setLcStartDate(DateUtil.getStringDate(rs.getDate(9), "yyyy-MM-dd"));
                wachatExpDomain.setReceiveDate(DateUtil.getStringDate(rs.getDate(10), "yyyy-MM-dd"));
                wachatExpDomain.setWayBillDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setBalanceDate(DateUtil.getStringDate(rs.getDate(12), "yyyy-MM-dd"));
                wachatExpDomain.setRefundTime(DateUtil.getStringDate(rs.getDate(13), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(14), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<CcIcExpDomain> getCcImccOrderExecl(String typeId, String orderCode, String startDate, String endDate, String supName, String name) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<CcIcExpDomain> listContents = new ArrayList<CcIcExpDomain>();
        try { 
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bi.company_name,o.order_code,bi.task_type,bi.custom_cl,bi.item_name,bi.items,bi.gross,bi.ship_name,bi.flight,bi.way_bill_no,bi.order_receiving_date,bi.bussiness_date,bi.in_finish_date,o.project_accomplish_date  ");
            sql.append(" From PM_OrderFrom o INNER JOIN CC_FLOW_IMCC bi on o.obj_Id=bi.order_from_id   ");
            sql.append("WHERE o.order_state!=5 AND o.tack_type_id=?");
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();  
            while (rs.next()) {
                CcIcExpDomain wachatExpDomain=new CcIcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setCustomCl(rs.getString(4));
                wachatExpDomain.setItemName(rs.getString(5));
                wachatExpDomain.setItems(rs.getString(6));
                wachatExpDomain.setGross(rs.getString(7));
                wachatExpDomain.setShipName(rs.getString(8));
                wachatExpDomain.setFlight(rs.getString(9));
                wachatExpDomain.setWayBillNo(rs.getString(10));
                wachatExpDomain.setOrderReceivingDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setBussinessDate(DateUtil.getStringDate(rs.getDate(12), "yyyy-MM-dd"));
                wachatExpDomain.setStoredFinishDate(DateUtil.getStringDate(rs.getDate(13), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(14), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<CcEcExpDomain> getCcEmccOrderExecl(String typeId, String orderCode, String startDate, String endDate, String supName, String name) {
        PreparedStatement psmt=null;
        ResultSet rs =null;
        Connection conn = null;
        List<CcEcExpDomain> listContents = new ArrayList<CcEcExpDomain>();
        try { 
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT bi.company_name,o.order_code,bi.task_type,bi.custom_cl,bi.item_name,bi.items,bi.gross,bi.ship_name,bi.flight,bi.way_bill_no,bi.order_receiving_date,bi.bussiness_date,bi.sign_date,o.project_accomplish_date  ");
            sql.append("From PM_OrderFrom o INNER JOIN CC_FLOW_EXCC bi on o.obj_Id=bi.order_from_id    ");
            sql.append("WHERE o.order_state!=5 AND o.tack_type_id=?");
            List list=new ArrayList();
            list.add(typeId);
            if (orderCode!=null&&!"".equals(orderCode)) {
                sql.append(" AND o.order_code  like ?");
                list.add("%"+orderCode+"%");
            }
            if (startDate!=null&&!"".equals(startDate)) {
                 sql.append("  AND o.obj_createDate>=? ");
                 list.add(DateUtil.formatDate(startDate, "yyyy-MM-dd"));
            }
            if (endDate!=null&&!"".equals(endDate)) {
                 sql.append("  AND o.obj_createDate<=? ");
                 list.add(DateUtil.formatDate(endDate, "yyyy-MM-dd"));
            }
            
           conn = SqlserverConnUtil.getConnection();
           psmt=conn.prepareStatement(sql.toString());
           for (int i = 0; i < list.size(); i++) {
               psmt.setObject(i+1, list.get(i));
           }
            rs = psmt.executeQuery();  
            while (rs.next()) {
                CcEcExpDomain wachatExpDomain=new CcEcExpDomain();
                wachatExpDomain.setName(rs.getString(1));
                wachatExpDomain.setTaskNun(rs.getString(2));
                wachatExpDomain.setTaskType(rs.getString(3));
                wachatExpDomain.setCustomCl(rs.getString(4));
                wachatExpDomain.setItemName(rs.getString(5));
                wachatExpDomain.setItems(rs.getString(6));
                wachatExpDomain.setGross(rs.getString(7));
                wachatExpDomain.setShipName(rs.getString(8));
                wachatExpDomain.setFlight(rs.getString(9));
                wachatExpDomain.setWayBillNo(rs.getString(10));
                wachatExpDomain.setOrderReceivingDate(DateUtil.getStringDate(rs.getDate(11), "yyyy-MM-dd"));
                wachatExpDomain.setBussinessDate(DateUtil.getStringDate(rs.getDate(12), "yyyy-MM-dd"));
                wachatExpDomain.setDriverSignDate(DateUtil.getStringDate(rs.getDate(13), "yyyy-MM-dd"));
                wachatExpDomain.setProjectAccomplishDate(DateUtil.getStringDate(rs.getDate(14), "yyyy-MM-dd"));
                listContents.add(wachatExpDomain);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           SqlserverConnUtil.closeConnect(rs, psmt, conn);
       }
        return listContents;
    }

    @Override
    public DingdanAddtion getOrderAddtion(String id) {
       
        return (DingdanAddtion)businApi.get(DingdanAddtion.class, id);
    }
    
}
