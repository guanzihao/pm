package com.pm.cms.busin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.HelpColumns;



/**
 * 网站内容
 * @author Administrator
 *
 */
public interface HelpColumnsBusin {
    
    
    void savehelpColumns(HelpColumns helpColumns,String helpColumnsid, MultipartFile img);
    
    void updatehelpColumns(HelpColumns helpColumns,String helpColumnsid, MultipartFile img);
    
    HelpColumns getHelpColumns(String id);
    
    void removehelpColumns(String id);
    
    List<HelpColumns>  getHelpColumnss(String columnparent);
   
}
