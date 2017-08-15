package com.sup.company.controller;

import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.core.model.TaskState;
import com.pm.core.util.StringUtil;
import com.pm.notice.bean.Notice;
import com.pm.noticeinformed.bean.NoticeInformed;
import com.sup.company.busin.CompanyInfoUserBusin;
/**
 * 客户公司登录首页
 * @author 李涛
 *
 */
@Controller
@RequestMapping("/sup/company/homePage")
public class CompanyIndexController extends SupBaseController {
    @Autowired
    private CompanyInfoUserBusin companyInfoUserBusin;
    /**
     * 显示客户公司的首页数据统计信息 
     * @return
     */
    @RequestMapping("/companyHome")
    public String showCompanyIndex(){
        CompanyInfoUser user=companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        List<NoticeInformed> infomationList=companyInfoUserBusin.getNoticeInformed();
        List<Notice> noticeList=companyInfoUserBusin.getNotice(user.getCompanyInfo().getId());
        Integer orderNum=companyInfoUserBusin.getComOrderNum(user.getCompanyInfo().getId());
        Integer ywcData=companyInfoUserBusin.getTotalCountByYwc(user.getCompanyInfo().getId(),TaskState.YWC);
        Integer ytjData=companyInfoUserBusin.getTotalCountByYtj(user.getCompanyInfo().getId(), TaskState.YTJ);
        Integer yfpData=companyInfoUserBusin.getTotalCountByYfp(user.getCompanyInfo().getId(), TaskState.YFP);
        model.addAttribute("informationData", infomationList);
        model.addAttribute("noticeData", noticeList);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("ytjData",ytjData);
        model.addAttribute("ywcData", ywcData);
        model.addAttribute("yfpData", yfpData);
        return "sup/homepage/companyIndex";
    }
    
    /**
     * 显示订单数的统计柱状图
     * 
     * @return
     */
    @RequestMapping("/searchOrderByCompany")
    @ResponseBody
    public void showOrderColum(String checkId) {
        List<Object[]> accountModelList=null;
        CompanyInfoUser user=companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        if(StringUtils.isEmpty(checkId)){
            accountModelList=companyInfoUserBusin.getNumByDay(user.getCompanyInfo().getId());
        }else{
            if("1".equals(checkId)){
                //按天汇总
                accountModelList=companyInfoUserBusin.getNumByDay(user.getCompanyInfo().getId());
            }else if("2".equals(checkId)){
                //按周汇总
                accountModelList=companyInfoUserBusin.getOrderNumberByWeek(user.getCompanyInfo().getId());
            }else if("3".equals(checkId)){
                accountModelList=companyInfoUserBusin.getOrderNumberByMonth(user.getCompanyInfo().getId());
            }else if("4".equals(checkId)){
                accountModelList=companyInfoUserBusin.getOrderNumberByQua(user.getCompanyInfo().getId());
            }
        }
        JSONArray accountData=getAccountNumber(accountModelList,checkId);
        writer(accountData.toString());
    }
    /**
     * 获取订单数的json数据
     * @param objectList
     * @param checkId
     * @return
     */
    public JSONArray  getAccountNumber(List<Object[]> objectList,String checkId){
        JSONArray numsArray = new JSONArray();
        if (objectList != null) {
            for (Object[] objects : objectList) {
                if (objects != null) {
                    
                    Integer num = (Integer) objects[1];
                    String date="";
                    if("1".equals(checkId)){
                        //按天汇总
                        Calendar now = Calendar.getInstance(); 
                        date = now.get(Calendar.YEAR)+"年"
                                +(now.get(Calendar.MONTH) + 1)+"月"
                                +objects[0]+"日";
                    }else if("2".equals(checkId)){
                        //按周汇总
                        date = objects[0]+"周";
                    }else if("3".equals(checkId)){
                        //按月汇总
                        date = objects[0] + "月";
                    }else if("4".equals(checkId)){
                        //按季汇总
                        date = "第"+objects[0] + "季";
                    }else{
                        //按天汇总
                        Calendar now = Calendar.getInstance(); 
                        date = now.get(Calendar.YEAR)+"年"
                                +(now.get(Calendar.MONTH) + 1)+"月"
                                +objects[0]+"日";
                    }
                    JSONObject numsJo = new JSONObject();
                    numsJo.put("name", date);
                    numsJo.put("num", num);
                    numsArray.put(numsJo);
                }
            }

        }
        return numsArray;
    }
    /**
     * 显示各类型单据在总订单数所占比重
     */
    @RequestMapping("/SearchBillPropByCompany")
    @ResponseBody
    public void showBillProportion() {
        JSONArray datasArray = new JSONArray();
        CompanyInfoUser user=companyInfoUserBusin.getCompanyInfoUser(getCurrSup().getId());
        List<Object[]> billList = companyInfoUserBusin.getBillProportion(user.getCompanyInfo().getId());
        if (billList != null) {
            for (Object[] objects : billList) {
                if (objects != null) {
                    JSONObject dataJo = new JSONObject();
                    String name = objects[0].toString();
                    Integer value = (Integer) objects[1];
                    String color=setColor(name);
                    dataJo.put("name", name);
                    dataJo.put("value", value);
                    dataJo.put("color", color);
                    datasArray.put(dataJo);
                }
            }

        }
        writer(datasArray.toString());
    }
    /**
     * 设置对应不同的订单类型的颜色码
     * @param name 订单类型名称
     * @return
     */
    public  String  setColor(String name){
          if(!StringUtil.isEmpty(name)){
              if(name.equals("出口报关")){
                  return "#e29c45";
              }else if(name.equals("进口报关")){
                  return "#44cef6"; 
              }else if(name.equals("仓储进库")){
                  return "#40de5a";
              }else if(name.equals("仓储出库")){
                  return "#6950a1";
              }else if(name.equals("外贸进口")){
                  return "#33a3dc";
              }else if(name.equals("外贸出口")){
                  return "#114777";
              }else if(name.equals("出口物流")){
                  return "#cca4e3";
              }else if(name.equals("进口物流")){
                  return "#0c8918"; 
              }else if(name.equals("物流运输")){
                  return "#fa8c35";
              }
          }
        return "";
    }
}
