package com.pm.inter.busin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.company.bean.CompanyInfo;
import com.pm.company.bean.SupCompanyInfo;
import com.pm.core.busin.BusinApi;
import com.pm.inter.bean.Interface;
import com.pm.inter.bean.InterfaceConfig;
import com.pm.inter.bean.InterfaceConfigModel;
import com.pm.inter.busin.InterfaceConfigBusin;
import com.pm.sysconfig.bean.Enumitems;
import com.pm.sysconfig.bean.Enumtype;
import com.pm.sysconfig.busin.EnumBusin;
@Service
public class InterfaceConfigBusinImpl implements InterfaceConfigBusin {
    
    @Autowired
    private BusinApi businApi;
    @Autowired
    private EnumBusin enumBusin;
    
    @Override
    public void saveInterfaceConfig(InterfaceConfig interConfig) {
        // TODO Auto-generated method stub
        businApi.save(interConfig);
    }

    @Override
    public void deleteIntefaceConfig(String id) {
        // TODO Auto-generated method stub
        InterfaceConfig interConfig = (InterfaceConfig) businApi.get(InterfaceConfig.class, id);
        if (interConfig != null) {
            businApi.remove(interConfig);
        }
    }

    @Override
    public InterfaceConfig getInterfaceConfig(String id) {
        return (InterfaceConfig)businApi.get(InterfaceConfig.class, id);
    }
    
    
   
    public List<Enumitems> findAllUseEnumitems(String id){
        Enumtype types=(Enumtype)businApi.get(Enumtype.class,id);
        List<Enumitems> enumitems = enumBusin.getEnumitemList(types);
        return enumitems;
    }
    
    @SuppressWarnings("unchecked")
    public List<Interface> findAllUseInterface(){
        List<Interface> interfaceList=businApi.getList(Interface.class);
        return interfaceList;
    }
    
    @SuppressWarnings("unchecked")
    public List<CompanyInfo> findAllUseCompanyInfo(){
        List<CompanyInfo> companys=businApi.getList(CompanyInfo.class);
        return companys;
    }
    
    @SuppressWarnings("unchecked")
    public List<SupCompanyInfo> findAllUseSup(){
        List<SupCompanyInfo> sups=businApi.getList(SupCompanyInfo.class);
        return sups;
    }
    
    public Enumitems getEnumitems(String id){
        
        Enumitems enumitem=(Enumitems)businApi.get(Enumitems.class, id);
        return enumitem;
    }
    
    public InterfaceConfigModel getInterfaceConfigModel(String id){
        InterfaceConfig interfaceConfig=(InterfaceConfig)businApi.get(InterfaceConfig.class, id);
        InterfaceConfigModel interfaceConfigModel=new InterfaceConfigModel();
        CompanyInfo company=(CompanyInfo)businApi.get(CompanyInfo.class,interfaceConfig.getCompanyId()); 
        if(company!=null){
            interfaceConfigModel.setCompanyName(company.getComName());
        }
        SupCompanyInfo sup=(SupCompanyInfo)businApi.get(SupCompanyInfo.class, interfaceConfig.getSuppersId());
        interfaceConfigModel.setContent(interfaceConfig.getContent());
        interfaceConfigModel.setCreateDate(interfaceConfig.getCreateDate());
        interfaceConfigModel.setEndDate(interfaceConfig.getEndDate());
        interfaceConfigModel.setId(interfaceConfig.getId());
        Interface inter=(Interface)businApi.get(Interface.class, interfaceConfig.getIntefaceId());
        if(inter!=null){
            interfaceConfigModel.setInterfaceName(inter.getIntername());
        }
        interfaceConfigModel.setInterfaceUrl(interfaceConfig.getInterfaceUrl());
        Enumitems joinWay=(Enumitems)businApi.get(Enumitems.class, interfaceConfig.getJoinType());
        if(joinWay!=null){
            interfaceConfigModel.setJoinName(joinWay.getName());
        }
        interfaceConfigModel.setSort(interfaceConfig.getSort());
        interfaceConfigModel.setStartDate(interfaceConfig.getStartDate());
        interfaceConfigModel.setStatus(interfaceConfig.getStatus());
        if(sup!=null){
            interfaceConfigModel.setSupperName(sup.getComName());
        }
        Enumitems accountType=(Enumitems)businApi.get(Enumitems.class, interfaceConfig.getAccountTypeId());
        if(accountType!=null){
            interfaceConfigModel.setTypeName(accountType.getName());
        }
        interfaceConfigModel.setUpdateDate(interfaceConfig.getUpdateDate());
       
       
        return interfaceConfigModel;
    }

}
