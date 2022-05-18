package com.demo2.demo2.controller.util;

import com.demo2.demo2.model.common.SuccessResponse;
import com.demo2.demo2.model.file.FileResponse;
import com.demo2.demo2.service.util.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/upload")
    public ResponseEntity<SuccessResponse<FileResponse>> uploadFile(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(new SuccessResponse<FileResponse>(fileStorageService.uploadFile(file)));
    }
    
    @GetMapping("/preview/{filename:.+}")
    public ResponseEntity<Resource> previewFile(@PathVariable String filename){
        Resource file = fileStorageService.downloadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        Resource file = fileStorageService.downloadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
