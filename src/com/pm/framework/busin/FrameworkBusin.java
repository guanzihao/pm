package com.pm.framework.busin;

import java.util.List;

import com.pm.core.bean.MetaObject;
import com.pm.framework.bean.IdentifyingCode;
import com.pm.framework.bean.MailConfig;
import com.pm.framework.bean.MailLog;
import com.pm.framework.bean.UploadFile;

/**
 * 业务层处理方法
 * 
 * @author FYL
 */

public interface FrameworkBusin {

    /**
     * SpringBean的名称
     */
    public static String FRAMEWORKBUSIN = "frameworkBusinImpl";

    /**
     * 生成流水号
     * 
     * @param code code
     * @return String
     */
    String getNumberLog(String code);

    /**
     * 邮件配置
     * 
     * @return MailConfig
     */
    MailConfig getMailConfig();

    /**
     * 发送邮件，并保存
     * 
     * @param mailLog MailLog
     */
    void sendMailLog(MailLog mailLog);

    /**
     * 删除附件
     * 
     * @param uploadFile
     */
    void removeUploadFile(UploadFile uploadFile);

    /**
     * 删除主体关联附件
     * 
     * @param metaObject 实体对象
     */
    void removeUploadFileOwnerList(MetaObject metaObject);

    /**
     * 删除附件关联的信息
     * 
     * @param uploadFile 附件
     */
    void removeUploadFileList(UploadFile uploadFile);

    /**
     * 获得附件集合
     * 
     * @param metaObject 实体对象
     * @return List
     */
    List<UploadFile> getUploadFileList(MetaObject metaObject, String metaColums);

    /**
     * 保存实体对象附件
     * 
     * @param metaObject 实体对象
     * @param ids 附件IDS
     */
    void saveUploadFileOwner(MetaObject metaObject, String[] ids);
    
    /**
     * 查询验证码表是否已经有这个邮箱的验证码(更具邮箱名称)
     * 
     * @return MailConfig
     */
    IdentifyingCode getIdentifyingCode(String emailName);
}
