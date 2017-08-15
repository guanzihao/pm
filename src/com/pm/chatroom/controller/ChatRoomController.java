package com.pm.chatroom.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.chatroom.bean.ChatRoom;
import com.pm.chatroom.busin.ChatRoomBusin;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.company.busin.CompanyInfoBusin;
import com.pm.core.model.Contexts;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;
import com.pm.organize.bean.UserAccount;
import com.pm.organize.controller.OrganizeBaseController;

/**
 * 平台聊天窗口
 * 
 * @author ZZY_SIVE
 *
 */
@Controller
@RequestMapping("/chatroom/chatroom")
public class ChatRoomController extends OrganizeBaseController {

    @Autowired
    private CompanyInfoBusin companyInfoBusin;

    @Autowired
    private ChatRoomBusin chatRoomBusin;

    /**
     * 在线聊天 选择聊天对象
     */
    @RequestMapping("/showChat")
    public String showChat() {
        model.addAttribute("userAccountList", organizeBusin.getUserAccountList());
        model.addAttribute("companyInfoList", companyInfoBusin.getAllCompanyInfoList(Contexts.Y));
        model.addAttribute("supCompanyInfoList", companyInfoBusin.getAllSupCompanyInfoList(Contexts.Y));
        return "/chatroom/chatroom/showChat";
    }

    /**
     * 发送聊天信息
     */
    @RequestMapping("/sendChat/{userIds}/{comIds}/{supIds}")
    public void sendChat(@PathVariable String userIds, @PathVariable String comIds, @PathVariable String supIds, String text) {
        if (!StringUtil.isEmpty(userIds)) {
            String[] nuserIds = userIds.split(",");
            for (String id : nuserIds) {
                UserAccount userAccount = organizeBusin.getUserAccount(id);
                if (userAccount != null) {
                    ChatRoom chatRoom = new ChatRoom();
                    chatRoom.setFromUserAccount(getCurrUser());
                    chatRoom.setToUserAccount(userAccount);
                    chatRoom.setText(text.trim());
                    chatRoom.setSubmitDate(new Date());
                    businApi.save(chatRoom);
                }
            }
        }

        if (!StringUtil.isEmpty(comIds)) {
            String[] ncomIds = comIds.split(",");
            for (String id : ncomIds) {
                CompanyInfo companyInfo = companyInfoBusin.getCompanyInfo(id);
                if (companyInfo != null) {
                    ChatRoom chatRoom = new ChatRoom();
                    chatRoom.setFromUserAccount(getCurrUser());
                    chatRoom.setToCompanyInfo(companyInfo);
                    chatRoom.setText(text.trim());
                    chatRoom.setSubmitDate(new Date());
                    businApi.save(chatRoom);
                }
            }
        }

        if (!StringUtil.isEmpty(supIds)) {
            String[] nsupIds = supIds.split(",");
            for (String id : nsupIds) {
                SupCompanyInfo supcompanyInfo = companyInfoBusin.getSupCompanyInfo(id);
                if (supcompanyInfo != null) {
                    ChatRoom chatRoom = new ChatRoom();
                    chatRoom.setFromUserAccount(getCurrUser());
                    chatRoom.setToSupCompanyInfo(supcompanyInfo);
                    chatRoom.setText(text.trim());
                    chatRoom.setSubmitDate(new Date());
                    businApi.save(chatRoom);
                }
            }
        }
        this.writer("Y");
    }

    /**
     * 聊天记录
     */
    @RequestMapping("/listChat")
    public void listChat() {
        StringBuffer buffer = new StringBuffer();
        List<ChatRoom> chatList = chatRoomBusin.getChatRoomListByUserAccount(getCurrUser());
        for (ChatRoom chat : chatList) {
            String str_sendEmployee = "";
            String str_toEmployee = "";
            //发送人
            if (chat.getFromUserAccount() != null) {
                str_sendEmployee = chat.getFromUserAccount().getUserName();
            } else if (chat.getFromCompanyInfo() != null) {
                str_sendEmployee = chat.getFromCompanyInfo().getComName() + "["+ chat.getCompanyInfoUser().getUserMail() +"]";
            } else if (chat.getFromSupCompanyInfo() != null) {
                str_sendEmployee = chat.getFromSupCompanyInfo().getComName() + "["+ chat.getCompanyInfoUser().getUserMail() +"]";
            }
            //接收人
            if (chat.getToUserAccount() != null) {
                str_toEmployee = chat.getToUserAccount().getUserName();
            } else if (chat.getToCompanyInfo() != null) {
                str_toEmployee = chat.getToCompanyInfo().getComName();
            } else if (chat.getToSupCompanyInfo() != null) {
                str_toEmployee = chat.getToSupCompanyInfo().getComName();
            }
            buffer.append(DateUtil.getStringDate(chat.getSubmitDate(), "yyyy-MM-dd HH:mm") + "  " + str_sendEmployee + " 对 " + str_toEmployee + " 说：\n");
            buffer.append("    " + chat.getText() + "\n");
        }
        this.writer(buffer.toString());
    }
}
