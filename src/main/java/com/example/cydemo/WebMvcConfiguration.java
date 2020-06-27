package com.example.cydemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//"file:///home/picResource/"
        registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:///Users/wangyunkun/Desktop/picResource/");//
    }
}

