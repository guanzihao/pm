package com.pm.inter.busin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pm.core.busin.BusinApi;
import com.pm.inter.bean.Interface;
import com.pm.inter.busin.InterfaceBusin;

@Service
public class InterfaceBusinImpl implements InterfaceBusin {
     @Autowired
     private BusinApi businApi;
     
     public void saveInterfaceColumns(Interface inter){
         businApi.save(inter);
     }
     
     public void deleteIntefaceColumns(String id){
         Interface inter = (Interface) businApi.get(Interface.class, id);
         if (inter != null) {
             businApi.remove(inter);
         }
     }
     
     public Interface getInterfaceColumn(String id){
         
         return (Interface) businApi.get(Interface.class, id);
     }
     
    
}
