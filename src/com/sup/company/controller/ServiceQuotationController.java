package com.sup.company.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pm.task.bean.AjaxDomain;
import com.sup.company.busin.ServiceQuotationBusin;
/**
 * 服务报价
 * @author litao
 *
 */
@Controller
@RequestMapping("/pm/servicequotation")
public class ServiceQuotationController extends SupBaseController{
    @Autowired
    private ServiceQuotationBusin serviceQuotationBusin;
    
    @RequestMapping(value = "/toServiceQuotation")  
    public String toQutationPage(){
        return "/homepage/indexHomePage";
    }
    /**
     * 查询报价管理信息
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/listServiceQuotation")  
    @ResponseBody
    public void  listServiceQutation(String pageIndex){
            String select="select top 4 a.obj_Id,a.origin_place,a.purpose_place,a.price,a.tonnage,sup.comName from PM_Supplier_Consignation_Quote a"
                    +" left join PM_COMPANY_SupCompanyInfo sup on a.supplier_id=sup.obj_Id";
             StringBuffer sql=new StringBuffer(select);  
             sql.append(" where  a.obj_Id not in ( select top ");
             sql.append("((?-1)*4)");
             sql.append(" a.obj_Id from PM_Supplier_Consignation_Quote a)");
             Integer index=1;
             if(!StringUtils.isEmpty(pageIndex)){
                 index=Integer.parseInt(pageIndex);
            }
             List<Object[]> serviceQuotationList=businApi.getSQLQuery(sql.toString(), new Object[]{index}).list();
            JSONArray arrays=new JSONArray();
             for(Object[] data:serviceQuotationList){
              JSONObject obj=new JSONObject();
                 if(data[1]==null){
                     obj.put("origin_place", "");
                 }else{
                     obj.put("origin_place", data[1]);
                 }
                 if(data[2]==null){
                     obj.put("purpose_place", "");
                 }else{
                     obj.put("purpose_place", data[2]);
                 }
                 obj.put("price", data[3]);
                 obj.put("tonnage", data[4]);
                 if(data[5]==null){
                     obj.put("comName", "");
                 }else{
                     obj.put("comName", data[5]);
                 }
                 arrays.put(obj);
             }
             writer(arrays.toString()); 
             
    }
    /**
     * 查询服务报价的总页码数
     */
    @RequestMapping(value = "/totalPageByQutation")  
    @ResponseBody
    public void getTotalPageByQutation(){
        //计算总条数
        String totalSql="select count(1) from PM_Supplier_Consignation_Quote a "
                       +" left join PM_COMPANY_SupCompanyInfo sup on a.supplier_id=sup.obj_Id";
        Integer totalSize=(Integer)businApi.getSQLQuery(totalSql, null).uniqueResult();
        //计算总页码数
        Integer totalPage=(totalSize%4==0)?(totalSize/4):(totalSize/4)+1;
        //JSONArray arrays=new JSONArray();
        JSONObject totalPageObj=new JSONObject();
        totalPageObj.put("totalPage", totalPage);
        writer(totalPageObj.toString());
    }
    /**
     * 查询报关委托协议
     * @param searchBean
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/listBgProxy")  
    @ResponseBody
    public void listBgProxyInfo(String pageIndex){
        Integer index=1;
        StringBuffer sql=new StringBuffer("select top 4 * from v_bgproxy a ");
        sql.append(" where a.obj_Id not in (select top (");
        if(!StringUtils.isEmpty(pageIndex)){
             index=Integer.parseInt(pageIndex);
        }
        sql.append("(?-1)*4)");
        sql.append(" obj_Id from v_bgproxy a) order by obj_createDate desc");
        List<Object[]> bgProxyList=businApi.getSQLQuery(sql.toString(), new Object[]{index}).list();
        JSONArray arrays=new JSONArray();
        for(Object[] data:bgProxyList){
            JSONObject obj=new JSONObject();
            obj.put("goodName", data[1]);
            obj.put("type", data[2]);
            obj.put("exDate", data[3]);
            obj.put("createDate", data[4]);
            arrays.put(obj);
        }
        
        writer(arrays.toString());
    }
    /**
     * 查询报关的总页码数
     */
    @RequestMapping(value = "/bgProxyCount")  
    @ResponseBody
    public void getBgProxyCount(){
        //计算总条数
        String totalSql="select count(1) from v_bgproxy ";
        Integer totalSize=(Integer)businApi.getSQLQuery(totalSql, null).uniqueResult();
        //计算总页码数
        Integer totalPage=(totalSize%4==0)?(totalSize/4):(totalSize/4)+1;
        //JSONArray arrays=new JSONArray();
        JSONObject totalPageObj=new JSONObject();
        
        totalPageObj.put("totalPage", totalPage);
        //arrays.put(totalPageObj);
        writer(totalPageObj.toString());
    }
    /**
     * 查询仓储的总页码数
     */
    @RequestMapping(value = "/ccProxyCount")  
    @ResponseBody
    public void getCcProxyCount(){
        //计算总条数
        String totalSql="select count(1) from v_ccproxy";
        Integer totalSize=(Integer)businApi.getSQLQuery(totalSql, null).uniqueResult();
        //计算总页码数
        Integer totalPage=(totalSize%4==0)?(totalSize/4):(totalSize/4)+1;
        JSONObject totalPageObj=new JSONObject();
        totalPageObj.put("totalPage", totalPage);
        writer(totalPageObj.toString());
    }
    
    /**
     * 查询物流的总页码数
     */
    @RequestMapping(value = "/wlProxyCount")  
    @ResponseBody
    public void getWlProxyCount(){
        //计算总条数
        String totalSql="select count(1) from v_wlproxy";
        Integer totalSize=(Integer)businApi.getSQLQuery(totalSql, null).uniqueResult();
        //计算总页码数
        Integer totalPage=(totalSize%4==0)?(totalSize/4):(totalSize/4)+1;
        JSONObject totalPageObj=new JSONObject();
        totalPageObj.put("totalPage", totalPage);
        writer(totalPageObj.toString());
    }
    
    /**
     * 查询外贸的总页码数
     */
    @RequestMapping(value = "/wmProxyCount")  
    @ResponseBody
    public void getWmProxyCount(){
        //计算总条数
        String totalSql="select count(1) from v_wmproxy";
        Integer totalSize=(Integer)businApi.getSQLQuery(totalSql, null).uniqueResult();
        //计算总页码数
        Integer totalPage=(totalSize%4==0)?(totalSize/4):(totalSize/4)+1;
        JSONObject totalPageObj=new JSONObject();
        totalPageObj.put("totalPage", totalPage);
        writer(totalPageObj.toString());
    }
    
    /**
     * 查询仓储委托协议
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/listCcProxy")  
    @ResponseBody
    public void listCcProxyInfo(String pageIndex){
        Integer index=1;
        StringBuffer sql=new StringBuffer("select top 4 * from v_ccproxy a ");
        sql.append(" where a.obj_Id not in (select top (");
        if(!StringUtils.isEmpty(pageIndex)){
             index=Integer.parseInt(pageIndex);
        }
        sql.append("(?-1)*4)");
        sql.append(" obj_Id from v_ccproxy a) order by obj_createDate desc");
        List<Object[]> bgProxyList=businApi.getSQLQuery(sql.toString(), new Object[]{index}).list();
        JSONArray arrays=new JSONArray();
        for(Object[] data:bgProxyList){
            JSONObject obj=new JSONObject();
            obj.put("destination", data[1]);
            obj.put("type", data[2]);
            obj.put("createDate", data[3]);
            arrays.put(obj);
        }
        
        writer(arrays.toString());
    }
    /**
     * 查询物流委托书协议
     * @param searchBean
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/listWlProxy") 
    @ResponseBody
    public void  listWlProxyInfo(String pageIndex){
        Integer index=1;
        StringBuffer sql=new StringBuffer("select top 4 * from v_wlproxy a ");
        sql.append(" where a.obj_Id not in (select top (");
        if(!StringUtils.isEmpty(pageIndex)){
             index=Integer.parseInt(pageIndex);
        }
        sql.append("(?-1)*4)");
        sql.append(" obj_Id from v_wlproxy a) order by obj_createDate desc");
        List<Object[]> bgProxyList=businApi.getSQLQuery(sql.toString(), new Object[]{index}).list();
        JSONArray arrays=new JSONArray();
        for(Object[] data:bgProxyList){
            JSONObject obj=new JSONObject();
            obj.put("departure_port", data[1]);
            obj.put("discharge_port", data[2]);
            obj.put("type", data[3]);
            obj.put("item", data[4]);
            obj.put("weight", data[5]);
            obj.put("volume", data[6]);
            obj.put("createDate",data[7]);
            arrays.put(obj);
        }
        
        writer(arrays.toString());
    }
    /**
     * 查询外贸委托书协议
     * @param searchBean
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/listWmProxy")
    @ResponseBody
    public  void  listWmProxyInfo(String pageIndex){
        Integer index=1;
        StringBuffer sql=new StringBuffer("select top 4 * from v_wmproxy a ");
        sql.append(" where a.obj_Id not in (select top (");
        if(!StringUtils.isEmpty(pageIndex)){
             index=Integer.parseInt(pageIndex);
        }
        sql.append("(?-1)*4)");
        sql.append(" obj_Id from v_wmproxy a) order by obj_createDate desc");
        List<Object[]> bgProxyList=businApi.getSQLQuery(sql.toString(), new Object[]{index}).list();
        JSONArray arrays=new JSONArray();
        for(Object[] data:bgProxyList){
            JSONObject obj=new JSONObject();
            obj.put("port_shipment", data[1]);
            obj.put("port_destination", data[2]);
            obj.put("type", data[3]);
            obj.put("delivery_time", data[4]);
            obj.put("obj_createDate", data[5]);
            arrays.put(obj);
        }
        
        writer(arrays.toString());
    }
    
    
   
    
}
