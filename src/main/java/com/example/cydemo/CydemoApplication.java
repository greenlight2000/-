package com.example.cydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class CydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CydemoApplication.class, args);
    }

}
