package com.example.root.testpack;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class TestController {

    @GetMapping("/test")
    public String testMethod() {
        return"Testpack/tests";
    }

    @GetMapping("/test1/{path}")
    public String t1(@PathVariable("path") String path1){
        System.out.println(path1);
        return "Testpack/"+path1;
    }

    @ResponseBody
    @GetMapping("/test/{path}")
    public void tes(@PathVariable("path") String path) throws IOException {
        String uploadPath = "/Users/dy/programming/springproject/copangclone/src/main/resources/templates/Testpack/";

        try{
            Path paths = Paths.get(uploadPath+"133.html");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(paths.toString() , false));
            bufferedWriter.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\"\n" +
                    "      xmlns:th=\"http://www.thymeleaf.org\"\n" +
                    "      xmlns:sec=\"http://www.thymeleaf.org/extras/spring-security\">\n" +
                    "<head th:replace=\"layout/header :: headLinkInit\"></head>\n" +
                    "<body>\n" +
                    "    <div>\n" +
                    "        <div th:replace=\"layout/top :: topmenu\"></div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div style=\"height: 250px;\"></div>\n" +
                    "\n" +
                    "    <div>\n" +
                    "        <h2>main AD slide, mouse position event</h2>\n" +
                    "        <img th:src=\"@{http://localhost:8080/image/ad/kakaocoding.png}\" width=\"300\" height=\"400\">\n" +
                    "        <img th:src=\"@{/image/ad/kakaocoding.png}\" width=\"300\" height=\"400\">\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div>\n" +
                    "        <h2>today md recommand</h2>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div>\n" +
                    "        <div th:replace=\"layout/footer :: footer\"></div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>\n" +
                    "</html>");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();

        }catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(path);

    }
}
