package com.pm.cms.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cms.bean.HelpColumns;
import com.pm.cms.bean.HelpContent;
import com.pm.cms.busin.HelpContentBusin;
import com.pm.cms.util.FileUtils;
import com.pm.core.busin.BusinApi;
import com.pm.framework.util.GlobalUtil;

@Service
@SuppressWarnings("unchecked")
public class HelpContentBusinImpl implements HelpContentBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public void saveCMSContent(HelpContent helpContent, String columnID, MultipartFile img, MultipartFile bigImg) {

        HelpColumns columns = new HelpColumns();
        columns.setId(columnID);
        helpContent.setCmsColumns(columns);
        String imgFileName = FileUtils.transferToFile(img, "/content");
        helpContent.setImg("content/"+imgFileName);
        String bigImgFileName = FileUtils.transferToFile(bigImg, "/content");
        helpContent.setBigImg("content/"+bigImgFileName);
        int num = 0;
        Query query = businApi.getQuery("SELECT  MAX(sort) FROM HelpContent ", null);
        try {
            List<Integer> list = query.list();
            if (list != null) {
                num = list.get(0) + 1;
            }
        } catch (Exception e) {
            
        }
        helpContent.setSort(num);
        businApi.save(helpContent);
    }

    @Override
    public void updateCMSContent(HelpContent helpContent, String columnID, MultipartFile img, MultipartFile bigImg) {
        HelpColumns columns = new HelpColumns();
        columns.setId(columnID);
        helpContent.setCmsColumns(columns);
        String imgFileName = FileUtils.transferToFile(img, "/content");
        if (!"".equals(imgFileName)) {
            FileUtils.deleteFile("/content", helpContent.getImg());
            helpContent.setImg("content/"+imgFileName);
        }
        String bigImgFileName = FileUtils.transferToFile(bigImg, "/content");
        if (!"".equals(bigImgFileName)) {
            FileUtils.deleteFile("/content", helpContent.getBigImg());
            helpContent.setBigImg("content/"+bigImgFileName);
        }
        businApi.save(helpContent);
    }

    @Override
    public void removeCMSContent(String id) {
        HelpContent content = (HelpContent) businApi.get(HelpContent.class, id);
        if (content != null) {
            businApi.remove(content);
        }
    }

    @Override
    public HelpContent getCMSContent(String id) {
        return (HelpContent) businApi.get(HelpContent.class, id);
    }

}
