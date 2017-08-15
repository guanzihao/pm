package com.pm.framework.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.pm.core.bean.MetaObject;
import com.pm.core.busin.GlobalContext;
import com.pm.framework.bean.UploadFile;
import com.pm.framework.busin.FrameworkBusin;

/**
 * 附件下载控制
 * 
 * @author FYL
 */

public class FileListTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(FileListTag.class);

    /**
     * MetaObject
     */
    private MetaObject metaObject;

    /**
     * 是否能删除
     */
    private boolean delete;

    /**
     * 属性名称
     */
    private String name;
    
    /**
     * 实体列metaColums
     */
    private String metaColums;

    public int doStartTag() throws JspException {
        if (metaObject != null) {
            FrameworkBusin frameworkBusin = (FrameworkBusin) GlobalContext.getSpringContext().getBean(
                    FrameworkBusin.FRAMEWORKBUSIN);
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            String basePath = (String) request.getAttribute("basePath");
            List<UploadFile> uploadFileList = frameworkBusin.getUploadFileList(metaObject, metaColums);
            if (uploadFileList != null && !uploadFileList.isEmpty()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("<ul class=\"list-unstyled project-files\">");
                for (UploadFile uploadFile : uploadFileList) {
                    buffer.append("<li id='li_" + uploadFile.getId() + "'>");

                    //下载
                    buffer.append("<a href='" + basePath + "/framework/file/downFile/" + uploadFile.getId()
                            + "' target='_bank'\"><i class=\"fa fa-file\"></i> " + uploadFile.getFileName() + " ("
                            + uploadFile.getFileSize() + "M)</a>");

                    //删除
                    if (delete) {
                        buffer.append("<input type='hidden' name='" + name + "' value='" + uploadFile.getId() + "'>");
                        buffer.append(" <a href='javascript:;' onclick=\"removedUploadFile('" + uploadFile.getId()
                                + "')\"><i class='fa fa-trash-o red'></i></a>");
                    }

                    buffer.append("</li>");
                }
                buffer.append("</ul>");
                try {
                    pageContext.getOut().println(buffer.toString());
                } catch (Exception e) {
                    LOGGER.error(e, e);
                }
            }
        }
        super.release();
        return TagSupport.SKIP_BODY;
    }

    public void setMetaObject(MetaObject metaObject) {
        this.metaObject = metaObject;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMetaColums(String metaColums) {
        this.metaColums = metaColums;
    }
}
