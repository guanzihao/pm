package com.pm.chatroom.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.chatroom.bean.ChatRoom;
import com.pm.chatroom.busin.ChatRoomBusin;
import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.organize.bean.UserAccount;

/**
 * 聊天室管理Service
 * 
 * @author ZZY_SIVE
 *
 */

@Service
@SuppressWarnings("unchecked")
public class ChatRoomBusinImpl implements ChatRoomBusin {

    @Autowired
    private BusinApi businApi;
    
    @Override
    public List<ChatRoom> getChatRoomListByUserAccount(UserAccount userAccount) {
        return businApi.getQueryList("from ChatRoom where (fromUserAccount.id = ? or toUserAccount.id = ?) order by createDate asc", new Object[] { userAccount.getId(), userAccount.getId() });
    }

    @Override
    public List<ChatRoom> getChatRoomListByCompanyInfo(CompanyInfo companyInfo) {
        return businApi.getQueryList("from ChatRoom where (fromCompanyInfo.id = ? or toCompanyInfo.id = ?) order by createDate asc", new Object[] { companyInfo.getId(), companyInfo.getId() });
    }

    @Override
    public List<ChatRoom> getChatRoomListBySupCompanyInfo(SupCompanyInfo supCompanyInfo) {
        return businApi.getQueryList("from ChatRoom where (fromSupCompanyInfo.id = ? or toSupCompanyInfo.id = ?) order by createDate asc", new Object[] { supCompanyInfo.getId(), supCompanyInfo.getId() });
    }

}
