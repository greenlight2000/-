package com.example.cydemo.controller;

import com.example.cydemo.entity.Food;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ImageController {

//    @RequestMapping("/listall")
//    public String listAll(User user,Model model) {
//        String str="%"+user.getUserName()+"%";
//        List<User> list = userRepository.listByName(str);
//        model.addAttribute("list", list);
//        return "list";
//    }

    @RequestMapping("/image")
    public String add(Food food, @RequestParam(value = "file", required = false) MultipartFile file,
                      HttpServletRequest request) throws IllegalStateException, IOException {
        if (!file.isEmpty()) {
            String str="src/main/resources/static/upload/";
            //String str="C:/Users/DELL/Desktop/ELK/upload/";
            String fileName = file.getOriginalFilename();
            //重命名
            fileName = UUID.randomUUID().toString().replace("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            //File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + fileName);
            File saveFile = new File(str + fileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }

            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(file.getBytes());
            out.flush();
            out.close();
            food.setFoodPic(fileName);
            System.out.println(food.toString());
//            userRepository.save(model);
//            return "redirect:list";
            return "if";

        } else {
//            userRepository.save(model);
//            return "redirect:list";
            return "else";
        }

    }
}
