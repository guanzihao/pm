package com.common.common.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.CompanyInfoUser;
import com.pm.sysconfig.bean.VocationalWorkEnum;
import com.sup.company.busin.ServiceQuotationBusin;
import com.sup.company.controller.SupBaseController;
/**
 * 服务报价
 * @author litao
 *
 */
@Controller
@RequestMapping("/common/servicequotation")
public class quotationController extends SupBaseController{
    @Autowired
    
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
    public void  listServiceQutation(String pageIndex,String flowType){
            String select="select top 4 a.obj_Id,a.origin_place,a.purpose_place,a.price,a.tonnage,sup.comName from PM_Supplier_Consignation_Quote a"
                    +" left join PM_COMPANY_SupCompanyInfo sup on a.supplier_id=sup.obj_Id"
                    +" left join PM_FlowType b on a.supplier_type=b.obj_Id";
             StringBuffer sql=new StringBuffer(select); 
             if(flowType.equals("物流")){
                 sql.append(" where a.check_state='1' and a.supplier_type  in(?,?,?) ");
             }else{
                 sql.append(" where a.check_state='1' and a.supplier_type  in(?,?) ");
             }
             sql.append("  and  a.obj_Id not in ( select top ");
             sql.append("((?-1)*4)");
             sql.append(" a.obj_Id from PM_Supplier_Consignation_Quote a ");
             if(flowType.equals("物流")){
                 sql.append(" where a.check_state='1' and a.supplier_type  in(?,?,?) ");
             }else{
                 sql.append(" where a.check_state='1' and a.supplier_type  in(?,?) ");
             }
             sql.append(" order BY a.obj_createDate)  order BY a.obj_createDate ");
             Integer index=1;
             if(!StringUtils.isEmpty(pageIndex)){
                 Integer n=Integer.parseInt(pageIndex);
                 if(n>1){
                     index=n;
                 }else{
                     index=1;
                 }
            }
             Object [] objects=null;
             if(flowType.equals("报关")){
                  objects=new Object []{VocationalWorkEnum.BG_IMCC.getId(),VocationalWorkEnum.BG_EXCC.getId(),index,VocationalWorkEnum.BG_IMCC.getId(),VocationalWorkEnum.BG_EXCC.getId()};
             }else if(flowType.equals("物流")){
                  objects=new Object[]{VocationalWorkEnum.WL_IMCC.getId(),VocationalWorkEnum.WL_EXCC.getId(),VocationalWorkEnum.WL_TRANSPORT.getId(),index,VocationalWorkEnum.WL_IMCC.getId(),VocationalWorkEnum.WL_EXCC.getId(),VocationalWorkEnum.WL_TRANSPORT.getId()};
             }else if(flowType.equals("仓储")){
                  objects=new Object[]{VocationalWorkEnum.CC_IMCC.getId(),VocationalWorkEnum.CC_EXCC.getId(),index,VocationalWorkEnum.CC_IMCC.getId(),VocationalWorkEnum.CC_EXCC.getId()};
             }else if(flowType.equals("外贸")){
                 objects=new Object[]{VocationalWorkEnum.WM_IMCC.getId(),VocationalWorkEnum.WM_EXCC.getId(),index,VocationalWorkEnum.WM_IMCC.getId(),VocationalWorkEnum.WM_EXCC.getId()};
             }
             List<Object[]> serviceQuotationList=businApi.getSQLQuery(sql.toString(), objects).list();
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
    public void getTotalPageByQutation(String flowType){
        //计算总条数
        String totalSql="select count(1) from PM_Supplier_Consignation_Quote a "
                       +" left join PM_COMPANY_SupCompanyInfo sup on a.supplier_id=sup.obj_Id"
                       +" left join PM_FlowType b on a.supplier_type=b.obj_Id";
        StringBuffer sql=new StringBuffer(totalSql);
        if(flowType.equals("物流")){
            sql.append(" where b.obj_Id  in(?,?,?)");
        }else{
            sql.append(" where b.obj_Id  in(?,?)");
        }
        Object [] objects=null;
        if(flowType.equals("报关")){
             objects=new Object []{VocationalWorkEnum.BG_IMCC.getId(),VocationalWorkEnum.BG_EXCC.getId()};
        }else if(flowType.equals("物流")){
             objects=new Object[]{VocationalWorkEnum.WL_IMCC.getId(),VocationalWorkEnum.WL_EXCC.getId(),VocationalWorkEnum.WL_TRANSPORT.getId()};
        }else if(flowType.equals("仓储")){
             objects=new Object[]{VocationalWorkEnum.CC_IMCC.getId(),VocationalWorkEnum.CC_EXCC.getId()};
        }else if(flowType.equals("外贸")){
            objects=new Object[]{VocationalWorkEnum.WM_IMCC.getId(),VocationalWorkEnum.WM_EXCC.getId()};
        }
        Integer totalSize=(Integer)businApi.getSQLQuery(sql.toString(), objects).uniqueResult();
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
             Integer n=Integer.parseInt(pageIndex);
             if(n>1){
                 index=n;
             }else{
                 index=1;
             }
        }
        sql.append("(?-1)*4)");
        sql.append(" obj_Id from v_bgproxy a) order by obj_createDate desc");
        List<Object[]> bgProxyList=businApi.getSQLQuery(sql.toString(), new Object[]{index}).list();
        JSONArray arrays=new JSONArray();
        for(Object[] data:bgProxyList){
            JSONObject obj=new JSONObject();
            if(data[1]==null){
                obj.put("goodName", "");
            }else{
                obj.put("goodName", data[1]);
            }
            
            obj.put("type", data[2]);
            if(data[3]==null){
                obj.put("exDate", "");
            }else{
                obj.put("exDate", data[3].toString().substring(0, 11));
            }
            if(data[4]==null){
                obj.put("createDate", "");
            }else{
                obj.put("createDate", data[4].toString().substring(0, 11)); 
            }
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
            if(data[1]==null){
                obj.put("destination", null);
            }else{
                obj.put("destination", data[1]);
            }
            obj.put("type", data[2]);
            if(data[3]==null){
                obj.put("createDate", "");
            }else{
                obj.put("createDate", data[3].toString().substring(0, 11)); 
            }
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
            if(data[4]==null){
                obj.put("item", "0");
            }else{
                obj.put("item", data[4]);
            }
            if(data[5]==null){
                obj.put("weight", "0");
            }else{
                obj.put("weight", data[5]);
            }
            if(data[6]==null){
                obj.put("volume", "0");
            }else{
                obj.put("volume", data[6]);
            }
            if(data[7]==null){
                obj.put("createDate", "");
            }else{
                obj.put("createDate", data[7].toString().substring(0, 11));
            }
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
            if(data[1]==null){
                obj.put("port_shipment", "");
            }else{
                obj.put("port_shipment", data[1]); 
            }
            if(data[2]==null){
                obj.put("port_destination", "");
            }else{
                obj.put("port_destination", data[2]); 
            }
            obj.put("type", data[3]);
            if(data[4]==null){
                obj.put("delivery_time", "");
            }else{
                obj.put("delivery_time", data[4].toString().substring(0, 11));
            }
            if(data[5]==null){
                obj.put("obj_createDate", "");
            }else{
                obj.put("obj_createDate", data[5].toString().substring(0, 11));
            }
            arrays.put(obj);
        }
        
        writer(arrays.toString());
    }
    /**
     * 显示网页内容
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/listWebContent")
    @ResponseBody
    public void getWebContent(){
        String sql="select top 3  content.tile,content.content,columns.img from CMS_Content content"
                  +" left join CMS_Columns columns on content.column_ID=columns.obj_Id"
                  +" where columns.name='政策法规' and status='0' order by sort desc";
        List<Object []> objects=businApi.getSQLQuery(sql, null).list();
        JSONArray arrays=new JSONArray();
        for(Object [] data:objects){
            JSONObject obj=new JSONObject();
            obj.put("title", data[0]);
            obj.put("content", data[1]);
            obj.put("big_img", data[2]);
            arrays.put(obj);
        }
        writer(arrays.toString());
    }
    /**
     * 验证客户是否登录,跳转至对应的立即下单页面
     * @return
     */
    @RequestMapping(value = "/orderImmediately")
    public String toCheckLogin(){
        CompanyInfoUser user=getCurrSup();
        if(user==null){
            model.addAttribute("urlMenthod","orderImmediately");
            return "/internet/userconfig/userLogin";
        }else{
            CompanyInfo company=user.getCompanyInfo();
            if(company!=null){
                if(company.getComState().equals("Y")){
                    model.addAttribute("url","/pm/sup/task/orderPrep");
                }
                return "/sup/main";
            }else{
                // model.addAttribute("url","/pm/sup/task/orderPrep");
                return "/sup/supmain";
            }
        }
    }
    
    
   
    
}
