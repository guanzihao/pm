package com.pm.cms.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.HelpColumns;
import com.pm.cms.busin.HelpColumnsBusin;
import com.pm.cms.util.FileUtils;
import com.pm.core.busin.BusinApi;
import com.pm.framework.util.GlobalUtil;

@Service
@SuppressWarnings("unchecked")
public class HelpColumnsBusinImpl implements HelpColumnsBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public void savehelpColumns(HelpColumns helpColumns, String helpColumnsid, MultipartFile img) {
        String imgFileName = FileUtils.transferToFile(img, "/content");
        helpColumns.setImg("content/"+imgFileName);
        int num = 0;
        Query query = businApi.getQuery("SELECT  MAX(columnSort) FROM HelpColumns ", null);
        try {
            List<Integer> list = query.list();
            if (list != null) {
                num = list.get(0) + 1;
            }
        } catch (Exception e) {
            
        }
        helpColumns.setColumnSort(num);
        businApi.save(helpColumns);
    }
    
    
    @Override
    public void updatehelpColumns(HelpColumns helpColumns, String helpColumnsid, MultipartFile img) {
        HelpColumns columns = new HelpColumns();
        columns.setId(helpColumnsid);
        String imgFileName = FileUtils.transferToFile(img, "/content");
        if (!"".equals(imgFileName)) {
            FileUtils.deleteFile("/content", helpColumns.getImg());
            helpColumns.setImg("content/"+imgFileName);
        }
        businApi.save(helpColumns);
    }


    @Override
    public HelpColumns getHelpColumns(String id) {
        return (HelpColumns) businApi.get(HelpColumns.class, id);
    }


    @Override
    public void removehelpColumns(String id) {
        HelpColumns content = (HelpColumns) businApi.get(HelpColumns.class, id);
        if (content != null) {
            businApi.remove(content);
        }
        
    }


    @Override
    public List<HelpColumns> getHelpColumnss(String columnparent) {
        
        List<HelpColumns> content = (List<HelpColumns>) businApi.getQueryList("from HelpColumns where columnparent=? and column_status=0 ",new Object[]{columnparent});
        return content;
    }

}
