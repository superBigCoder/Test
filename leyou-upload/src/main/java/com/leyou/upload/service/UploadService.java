package com.leyou.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Arrays;
import java.util.List;


@Service
public class UploadService {
    private final List<String> CONTENTTYPE= Arrays.asList("image/gif","image/jpeg");
    private final Logger LOGGER= LoggerFactory.getLogger(UploadService.class);
    public String upLoadImage(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
           if(!CONTENTTYPE.contains(contentType)){
               LOGGER.info("文件类型不合法{}",originalFilename);
               return null;
           }
        try {
            if(ImageIO.read(file.getInputStream())==null){
                LOGGER.info("文件内容不合法{}",originalFilename);
                return null;
            }
            file.transferTo(new File("G://Leyou//image"+originalFilename));
            return "http://image.leyou.com/"+originalFilename;
        } catch (Exception e) {
            LOGGER.info("服务器上传{}失败",originalFilename);
            e.printStackTrace();
        }
           return null;

    }
}
