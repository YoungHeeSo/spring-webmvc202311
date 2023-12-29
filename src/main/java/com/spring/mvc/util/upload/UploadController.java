package com.spring.mvc.util.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.spring.mvc.util.upload.FileUtil.*;

@Controller
@Slf4j
public class UploadController {

    private String rootPath = "/Users/youngheeso/spring-prj/upload";

    @GetMapping("/upload-form")
    public String uploadForm(){
        return "upload/upload-form";
    }

    @PostMapping("/upload-file")
    public String uploadForm(@RequestParam("thumbnail") MultipartFile file){

        log.info("file-name: {}", file.getOriginalFilename());
        log.info("file-size: {}KB", file.getSize() / 1024.0);
        log.info("file-type: {}", file.getContentType());

        // 첨부파일을 서버 스토리지에 저장
        // 1. root 디렉토리를 생성
        File root = new File(rootPath);
        if(!root.exists()) root.mkdirs();

        /*
        // 2. 첨부파일을 파일 객체로 생성
        File uploadFile = new File(rootPath, file.getOriginalFilename());

        // 3. 파일을 해당 경로에 저장
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        uploadFile(file, rootPath);

        return "";
    }
}
