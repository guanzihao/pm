package com.pm.cms.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.pm.framework.util.FileUtil;

/**
 * 上傳文件工具類
 * 
 * @author Administrator
 *
 */
public class FileUtils {
    
    /**
     * 上传文件
     * @param multipartFile 文件
     * @param filePath 文具相对于根的路径
     * @param path2 
     * @return
     */
    public static String transferToFile(MultipartFile multipartFile, String filePath) {
        String path = FileUtil.getFilePath();
        path = path + filePath;
        File isFile = new File(path);
        if (!isFile.exists() && !isFile.isDirectory()) {
            isFile.mkdirs();
        }
        String fileName = "";
        if (!multipartFile.isEmpty()) {

            String originalFilename = multipartFile.getOriginalFilename();
            String picId = String.valueOf(UUID.randomUUID()).replace("-", "");
            fileName = picId + originalFilename.substring(originalFilename.lastIndexOf("."));
            File file = new File(path + "/" + fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
    
    
    /**
     * 删除一个文件
     * @param filePath 相对于跟路径的路径
     * @param fileName 文件名称
     */
    public static void deleteFile(String filePath,String fileName) {
        try {
            String path = FileUtil.getFilePath();
            path = path + filePath;
            //删除原有文件
            File oldFile=new File(path+"/"+fileName);
            oldFile.delete();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }

}
