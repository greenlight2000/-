package com.example.cydemo.controller;


import com.example.cydemo.dao.FoodSpecDao;
import com.example.cydemo.dto.FoodSpecDto;
import com.example.cydemo.entity.FoodSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/TestBase")
public class TestBaseController extends BaseController<FoodSpec> {
    //手动注入dao
    FoodSpecDao foodSpecDao;
    @Autowired
    public TestBaseController(FoodSpecDao foodSpecDao)
    {
        this.foodSpecDao = foodSpecDao;
        init(this.foodSpecDao);
    }


    @GetMapping("/getIt")
    public List<FoodSpecDto> getIt(@RequestParam("key")String key, @RequestParam("value")String value, @RequestParam("isFuzzy")boolean isFuzzy)
    {
        List<FoodSpec> entityList = super.searchTs(key,value,isFuzzy);
        List<FoodSpecDto> dtoList = new ArrayList<>();
        //entity -> dto
        for(FoodSpec f : entityList)
        {
            dtoList.add(f.toDto());
        }
        return dtoList;
    }
}
