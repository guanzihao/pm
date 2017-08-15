package com.sup.flow.busin;

import java.util.List;




import org.hibernate.SQLQuery;

import com.pm.organize.bean.UserAccount;
import com.sup.flow.bean.Model;

public interface EmailPostBusin {
    
    
    /**
     * 查询收件人的数据
     * @return
     */
    List<Model> getEmailInfo();
    
    
    /**
     * 修改已发送邮件的状态
     * @return
     */
    SQLQuery setNodeTYpe(Model model);
    
    
    /**
     * 查询系统管理员
     * @return
     */
    UserAccount getUserAccount();
}
