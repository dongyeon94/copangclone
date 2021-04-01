package com.example.root.controller.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Slf4j
@Controller
public class FileController {

    @GetMapping("/file/upload")
    public String fileUpload() {
        return "test/uploadFile";
    }

    @PostMapping("/file/upload")
    public String uploadFile(MultipartFile[] fileUpload1) throws Exception {
        String uploadPath = "/Users/dy/programming/springproject/copangclone/src/main/resources/static/image/product/";
        try {
            for (MultipartFile f1 :fileUpload1){
                File saveFile = new File(uploadPath,f1.getOriginalFilename());
                f1.transferTo(saveFile);

                log.info("file name : "+ f1.getOriginalFilename());
                log.info("file size : "+ f1.getSize());
            }
        }catch (Exception e){
            log.info("error : " + e.getMessage());
        }
        return "redirect:/file/upload";
    }

}
