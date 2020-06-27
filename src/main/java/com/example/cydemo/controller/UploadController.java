package com.example.cydemo.controller;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.springframework.boot.configurationprocessor.json.JSONStringer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "files", required = false) MultipartFile uploadFile, HttpServletRequest request) {
        //定义存储路径
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File("/Users/wangyunkun/Desktop/picResource");//"/home/picResource"
        System.out.println("realPath: "+realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
            System.out.println("here");
            System.out.println(dir);
        }

        try {
            String filename = uploadFile.getOriginalFilename();
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            System.out.println("file文件真实路径:" + fileServer.getAbsolutePath());
            //2，实现上传
            uploadFile.transferTo(fileServer);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort()
                    + "/uploadFile/" + filename;
            System.out.println("filePath:"+filePath);
            //3，返回可供访问的网络路径
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }


}
