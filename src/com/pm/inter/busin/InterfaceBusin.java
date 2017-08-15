package com.pm.inter.busin;


import com.pm.inter.bean.Interface;

/**
 * 接口表业务处理
 * @author Administrator
 *
 */
public interface InterfaceBusin {
    public  void saveInterfaceColumns(Interface inter);
    
    public void deleteIntefaceColumns(String  id);
    
    public Interface getInterfaceColumn(String id); 
    
    
    
    
}
