package com.example.root.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Controller
public class FileController {

    @GetMapping("/file/upload")
    public String fileUpload() {
        return "test/uploadFile";
    }

    @PostMapping("/file/upload")
    public String uploadFile(MultipartFile[] fileUpload1) throws Exception {
        System.out.println(fileUpload1);
        try {
            System.out.println(fileUpload1.toString());
            for (MultipartFile f1 :fileUpload1){
                System.out.println(f1.getOriginalFilename());
                System.out.println(f1.getSize());
            }
        }catch (Exception e){
            System.out.println("error : " + e.getMessage());
        }
        return "redirect:/file/upload";
    }

}
