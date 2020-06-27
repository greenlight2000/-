package com.example.cydemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerTest {
    @GetMapping
    public String runServer(){
        return "success";
    }
}
