package com.mde.service.impl;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.mde.service.IUploadService;
import com.mde.util.DateUtil;

@Service
public class UploadServiceImpl implements IUploadService
{
    @Override
    public String upload(String filename, File file, String rootPath)
    {
        // 设置上传目录
        File rootDir = new File(rootPath);
        File uploadPath = new File(rootDir, "/upload/images/" + DateUtil.format(new Date(), "yyyyMMdd"));
        
        if (!uploadPath.exists())
        {
            if (!uploadPath.mkdirs())
            {
                throw new IllegalStateException();
            }
        }
        
        try
        {
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            String timestamp = DateUtil.format(new Date(), "HHmmss");
            
            String imageFileName = String.format("%1$s.%2$s", timestamp, suffix);
            File image = new File(uploadPath, imageFileName);
            FileUtils.copyFile(file, image);
            String imageFilePath = image.getAbsolutePath();
            return imageFilePath.substring(imageFilePath.indexOf("upload"));
        }
        catch (Exception e)
        {
            throw new IllegalStateException();
        }
    }
    
}
