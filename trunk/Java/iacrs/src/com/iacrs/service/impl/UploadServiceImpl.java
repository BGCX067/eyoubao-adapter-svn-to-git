package com.iacrs.service.impl;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iacrs.exception.ServiceException;
import com.iacrs.exception.ServiceExceptionCode;
import com.iacrs.service.IUploadService;
import com.iacrs.util.DateUtil;

@Service
public class UploadServiceImpl implements IUploadService
{
    private static Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);
    
    @Override
    public String upload(MultipartFile file, String rootPath)
    {
        // 设置上传目录
        File rootDir = new File(rootPath);
        File uploadPath = new File(rootDir, "/upload/cars/" + DateUtil.format(new Date(), "yyyyMMdd"));
        
        if (!uploadPath.exists())
        {
            if (!uploadPath.mkdirs())
            {
                log.error("Can not make advertising image upload directory.");
                throw new ServiceException(ServiceExceptionCode.IACRS_0003);
            }
        }
        
        try
        {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String timestamp = DateUtil.format(new Date(), "HHmmss");
            
            String imageFileName = String.format("%1$s.%2$s", timestamp, suffix);
            File image = new File(uploadPath, imageFileName);
            file.transferTo(image);
            String imageFilePath = image.getAbsolutePath();
            return imageFilePath.substring(imageFilePath.indexOf("upload"));
        }
        catch (Exception e)
        {
            log.error("Upload file transfer to local file error.", e);
            throw new ServiceException(ServiceExceptionCode.IACRS_0004);
        }
    }
}
