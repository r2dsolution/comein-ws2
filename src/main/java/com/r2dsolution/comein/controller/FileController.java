package com.r2dsolution.comein.controller;

import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.r2dsolution.comein.aws.S3Service;
import com.r2dsolution.comein.dto.FileUploadDto;

@Controller
public class FileController {
	private static Logger log = LoggerFactory.getLogger(FileController.class);
	
	private static final String ATTR_USER_TOKEN = "userToken";
	
	@Autowired
	S3Service s3Service;
	
    @PostMapping("/files")
    public ResponseEntity<FileUploadDto> uploadFile(@RequestHeader(ATTR_USER_TOKEN) String userToken, 
    		@RequestParam("module") String module,
    		@RequestParam("file") MultipartFile multipart) {
    	
        String fileName = multipart.getOriginalFilename();
        String extension = FilenameUtils.getExtension(multipart.getOriginalFilename());
        String uuidFileName = UUID.randomUUID().toString()+"."+extension;
        String contentType = multipart.getContentType();
        long size = multipart.getSize();
         
        log.info("module: {}", module);
        log.info("filename: {}, uuidFileName : {}", fileName, uuidFileName);
        log.info("contentType: {}", contentType);
        log.info("size: {}", size);
        
        FileUploadDto res = new FileUploadDto();
        res.setModule(module);
        res.setFileName(uuidFileName);
        
        String message = "";
         
        try {
        	this.s3Service.uploadFile(module, uuidFileName, contentType, multipart.getInputStream());
            message = "Your file has been uploaded successfully!";
        } catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }
        res.setMessage(message);
        log.info("message: {}", message);
         
        return ResponseEntity.ok(res);
    }
    
    @DeleteMapping("/files")
    public ResponseEntity<FileUploadDto> deleteFile(@RequestHeader(ATTR_USER_TOKEN) String userToken, 
    		@RequestBody FileUploadDto req) {
         
        log.info("module : {}, filename: {}", req.getModule(), req.getFileName());
        
        String message = "";
        String module = req.getModule();
    	String fileName = req.getFileName();
         
        FileUploadDto res = new FileUploadDto();
        res.setModule(module);
        res.setFileName(fileName);
        
        try {
        	this.s3Service.deleteFile(module, fileName);
            message = "Your file has been deleted successfully!";
        } catch (Exception ex) {
            message = "Error delete file: " + ex.getMessage();
        }
        res.setMessage(message);
        log.info("message: {}", message);
         
        return ResponseEntity.ok(res);
    }
	
}
