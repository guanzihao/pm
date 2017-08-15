package com.sup.flow.busin.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.core.busin.BusinApi;
import com.pm.organize.bean.UserAccount;
import com.sup.flow.bean.Model;
import com.sup.flow.busin.EmailPostBusin;

@Service
public class EmailPostBusinImpl implements EmailPostBusin {
    
    
    @Autowired
    private BusinApi businApi;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Model> getEmailInfo() {
        String isemail="0";
        return (List<Model>)businApi.getQueryList("from Model where isEmail = ?",new Object[]{isemail});
    }


    @Override
    public SQLQuery setNodeTYpe(Model model) {
        StringBuffer sql=new StringBuffer("update ");
        sql.append(model.getTablename());
        sql.append(" set is_sendEmail='1' where obj_id = ?");
        
       
        
      return businApi.getSQLQuery(sql.toString(),new Object[]{model.getObjids()});
    }


    @Override
    public UserAccount getUserAccount() {
        String id="user_admin";
        return (UserAccount)businApi.get(UserAccount.class, id);
    }
   
}
