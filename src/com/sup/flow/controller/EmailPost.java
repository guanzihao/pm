package com.sup.flow.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.company.bean.CompanyInfoUser;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.busin.BusinApi;
import com.pm.framework.bean.MailLog;
import com.pm.framework.busin.FrameworkBusin;
import com.pm.framework.util.MailTempUtil;
import com.pm.notice.bean.Notice;
import com.pm.notice.bean.NoticeUser;
import com.pm.organize.bean.UserAccount;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.bean.Systemplate;
import com.pm.sysconfig.busin.EnumBusin;
import com.pm.sysconfig.busin.SystemplateBusin;
import com.sup.flow.bean.BgFlowExcc;
import com.sup.flow.bean.BgFlowExccNode;
import com.sup.flow.bean.BgFlowImcc;
import com.sup.flow.bean.BgFlowImccNode;
import com.sup.flow.bean.CcFlowExcc;
import com.sup.flow.bean.CcFlowExccNode;
import com.sup.flow.bean.CcFlowImcc;
import com.sup.flow.bean.CcFlowImccNode;
import com.sup.flow.bean.Model;
import com.sup.flow.bean.WlFlowExcc;
import com.sup.flow.bean.WlFlowExccNode;
import com.sup.flow.bean.WlFlowImcc;
import com.sup.flow.bean.WlFlowImccNode;
import com.sup.flow.bean.WlFlowTransport;
import com.sup.flow.bean.WlFlowTransportNode;
import com.sup.flow.bean.WmFlowExcc;
import com.sup.flow.bean.WmFlowExccNode;
import com.sup.flow.bean.WmFlowImcc;
import com.sup.flow.bean.WmFlowImccNode;
import com.sup.flow.busin.EmailPostBusin;
import com.sup.order.bean.OrderFrom;

@Component
public class EmailPost {
    @Autowired
    private BusinApi businApi;
    
    @Autowired
    private EnumBusin enumBusin;
    
    @Autowired
    private EmailPostBusin emailPostBusin;
    
    @Autowired
    private SystemplateBusin systemplateBusin;
    
    @Autowired
    public FrameworkBusin frameworkBusin;
    
    @Autowired
    public com.sup.flow.busin.BgFlowImccBusin BgFlowImccBusin;
    
    @Autowired
    public CompanyInfoBusin companyInfoBusin;
    
    /**
     * 给需要发送邮件的并且没有发送邮件的节点发送邮件
     */
    public void email() {
        List<Model> model = emailPostBusin.getEmailInfo();
        if(model!=null){
            for (Model model2 : model) {
                Companyemail(model2.getEmailType(),model2.getEmail(),model2.getTablename(),model2.getObjids(),model2.getOktime());
               
                emailPostBusin.setNodeTYpe(model2);
                model2.setIsEmail("1");
                businApi.save(model2);
                 //添加站内消息
                Notice notice=new Notice();
                notice.setNoticeTitle("节点发送邮件");
                notice.setNoticeText(model2.getEmailType()+"节点信息已发送给"+"邮箱为"+model2.getEmail()+"用户");
                notice.setNoticeIsRead(true);
                UserAccount user=emailPostBusin.getUserAccount();
                notice.setUserAccount(user);
                businApi.save(notice);
                //添加noticeuser信息
                NoticeUser noticeUser=new NoticeUser();
                noticeUser.setNotice(notice);
                noticeUser.setUserAccount(user);
                businApi.save(noticeUser);
                
            }
        }
    }
    
    
    /**
     * 选择邮件模板，以及内容 
     * @param emailinfo  邮件内容
     * @param companyuser  收件人
     */
    @SuppressWarnings("null")
    public void Companyemail(String emailinfo,String companyuser,String tablename,String objid,Date oktime){
        //给需要发送邮件的节点发送
        Enumtype enumType = enumBusin.getEnumtypeByName("邮件信息枚举");
        if(enumType!=null){
            List<Enumitems> enumitems = enumBusin.getEnumitemList(enumType);
            if(enumitems!=null){
                for (Enumitems enumitems2 : enumitems) {
                    if(enumitems2.getName().equals("流程节点邮件")){
                        
                        OrderFrom orderFrom=null;
                        switch (tablename) {
                        case "BG_Flow_EXCC":
                            BgFlowExccNode bgFlowExccNode=(BgFlowExccNode)businApi.get(BgFlowExccNode.class, objid);
                            BgFlowExcc bgFlowExcc=(BgFlowExcc)businApi.get(BgFlowExcc.class, bgFlowExccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, bgFlowExcc.getOrderFrom());
                            break;
                        case "WL_Flow_EXCC":
                            WlFlowExccNode wlFlowExccNode=(WlFlowExccNode)businApi.get(WlFlowExccNode.class, objid);
                            WlFlowExcc wlFlowExcc=(WlFlowExcc)businApi.get(WlFlowExcc.class, wlFlowExccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, wlFlowExcc.getOrderFromId());
                            break;
                        case "BG_Flow_IMCC":
                            BgFlowImccNode bgFlowImccNode=(BgFlowImccNode)businApi.get(BgFlowImccNode.class, objid);
                            BgFlowImcc cgFlowImcc=(BgFlowImcc)businApi.get(BgFlowImcc.class, bgFlowImccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, cgFlowImcc.getOrderFrom());
                            break;
                        case "WM_Flow_IMCC":
                            WmFlowImccNode wmFlowImccNode=(WmFlowImccNode)businApi.get(WmFlowImccNode.class, objid);
                            WmFlowImcc wmFlowImcc=(WmFlowImcc)businApi.get(WmFlowImcc.class, wmFlowImccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, wmFlowImcc.getOrderFromId());
                            break;
                        case "WL_Flow_IMCC":
                            WlFlowImccNode wlFlowImccNode=(WlFlowImccNode)businApi.get(WlFlowImccNode.class, objid);
                            WlFlowImcc wlFlowImcc=(WlFlowImcc)businApi.get(WlFlowImcc.class, wlFlowImccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, wlFlowImcc.getOrderFromId());
                            break;
                        case "WL_Flow_Transport":
                            WlFlowTransportNode wlFlowTransportNode=(WlFlowTransportNode)businApi.get(WlFlowTransportNode.class, objid);
                            WlFlowTransport wlFlowTransport=(WlFlowTransport)businApi.get(WlFlowTransport.class, wlFlowTransportNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, wlFlowTransport.getOrderFromId());
                            break;
                        case "CC_Flow_EXCC":
                            CcFlowExccNode ccFlowExccNode=(CcFlowExccNode)businApi.get(CcFlowExccNode.class, objid);
                            CcFlowExcc ccFlowExcc=(CcFlowExcc)businApi.get(CcFlowExcc.class, ccFlowExccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, ccFlowExcc.getOrderFromId());
                            break;
                        case "CC_Flow_IMCC":
                            CcFlowImccNode ccFlowImccNode=(CcFlowImccNode)businApi.get(CcFlowImccNode.class, objid);
                            CcFlowImcc ccFlowImcc=(CcFlowImcc)businApi.get(CcFlowImcc.class, ccFlowImccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, ccFlowImcc.getOrderFromId());
                            break;
                        case "WM_Flow_EXCC":
                            WmFlowExccNode wmFlowExccNode=(WmFlowExccNode)businApi.get(WmFlowExccNode.class, objid);
                            WmFlowExcc wmFlowExcc=(WmFlowExcc)businApi.get(WmFlowExcc.class, wmFlowExccNode.getBillId());
                            orderFrom=(OrderFrom)businApi.get(OrderFrom.class, wmFlowExcc.getOrderFromId());
                            break;
                        
                        }
                        
                        CompanyInfoUser companyInfouser=companyInfoBusin.getCompanyInfouser(companyuser);
                        //获取邮件模板
                        Systemplate systemplate=systemplateBusin.getSystemplatetype(enumitems2.getId());
                        String mailText = systemplate.getTempcontet();
                       
                        mailText = MailTempUtil.fillParams(mailText, "code", emailinfo);
                        mailText = MailTempUtil.fillParams(mailText, "codeuser", companyInfouser.getUserName());
                        mailText = MailTempUtil.fillParams(mailText, "ordercode", orderFrom.getOrderCode());
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
                        String str=sdf.format(oktime);   
                        mailText = MailTempUtil.fillParams(mailText, "oktime", str);
                        
                        MailLog mailLog = new MailLog();
                        mailLog.setMailSubject("云贸通-流程节点邮件");
                        mailLog.setMailBody(mailText);
                        mailLog.setMailToId(companyuser);
                        frameworkBusin.sendMailLog(mailLog);
                    }
                }
            }
        }
    }
    
    
    
}
