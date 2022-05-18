package com.demo2.demo2.service.util;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.demo2.demo2.constant.StatusCode;
import com.demo2.demo2.controller.util.FileController;
import com.demo2.demo2.exception.BaseException;
import com.demo2.demo2.model.file.FileResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Service
public class FileStorageService {

    @Value("${app.file.upload.path}")
    private String fileUploadPath;

    public FileResponse uploadFile(MultipartFile file){
        if(file == null){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        String contentType = file.getContentType();
        List<String> allowContentTypes = Arrays.asList("image/jpeg", "image/jpg");
        if(!allowContentTypes.contains(contentType)){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        try{
            Path uploads = Paths.get(fileUploadPath);
            if(Files.exists(uploads)){
                    Files.createDirectories(uploads);
            }
            Path filePath = uploads.resolve(file.getOriginalFilename());
            if(!(Files.exists(filePath))){
                Files.copy(file.getInputStream(), filePath);
            }
            Resource resource = new UrlResource(filePath.toUri());
            if((resource.exists()) && (resource.isReadable())){
                // "urlPreview": "http://localhost:8080/api/v1/file/preview/124785.jpg",
                String urlPreview = MvcUriComponentsBuilder.fromMethodName(FileController.class, "previewFile", filePath.getFileName().toString()).build().toString();
                    
                // "urlDownload": "http://localhost:8080/api/v1/file/download/124785.jpg"            
                String urlDownload = MvcUriComponentsBuilder.fromMethodName(FileController.class, "downloadFile", filePath.getFileName().toString()).build().toString();                
                
                FileResponse fileResponse = new FileResponse();
                fileResponse.setFileName(filePath.getFileName().toString());
                fileResponse.setUrlPreview(urlPreview);
                fileResponse.setUrlDownload(urlDownload);
                return fileResponse;
            }
        }catch (Exception e) {
                e.printStackTrace();
                throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        return null;
    }

    public Resource downloadFile(String filename){
        try {
            Path uploads = Paths.get(fileUploadPath);
            Path filePath = uploads.resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if((resource.exists()) && (resource.isReadable())){
                return resource;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, StatusCode.ERR_CODE_500, StatusCode.ERR_DESC_500);
        }
        return null;
    }
}
