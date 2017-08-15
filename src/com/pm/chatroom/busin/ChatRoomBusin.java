package com.pm.chatroom.busin;

import java.util.List;

import com.pm.chatroom.bean.ChatRoom;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.organize.bean.UserAccount;

/**
 * 聊天室管理
 * 
 * @author ZZY_SIVE
 *
 */

public interface ChatRoomBusin {

    /**
     * SpringBean的名称
     */
    public static String CHATROOMBUSIN = "chatRoomBusinImpl";

    /**
     * 平台查看聊天记录
     * @param userAccount
     * @return
     */
    List<ChatRoom> getChatRoomListByUserAccount(UserAccount userAccount);

    /**
     * 客户查看聊天记录
     * @param companyInfo
     * @return
     */
    List<ChatRoom> getChatRoomListByCompanyInfo(CompanyInfo companyInfo);
    
    /**
     * 供应商查看聊天记录
     * @param companyInfo
     * @return
     */
    List<ChatRoom> getChatRoomListBySupCompanyInfo(SupCompanyInfo supCompanyInfo);
}
