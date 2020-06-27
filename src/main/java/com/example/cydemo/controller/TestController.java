package com.example.cydemo.controller;

import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.dao.FoodSpecDao;
import com.example.cydemo.dto.FoodDto;
import com.example.cydemo.entity.Food;
import com.example.cydemo.entity.FoodSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController//测试 OneToMany
{
    @Autowired
    private FoodDao foodDao;

    @Autowired
    private FoodSpecDao foodSpecDao;

    @GetMapping("/add")
    public String testAdd()
    {
        Food food = new Food();
        food.setFoodName("红烧肉盖饭");

        FoodSpec foodSpec = new FoodSpec();
        foodSpec.setSpecName("煎蛋");
        //food.getFoodSpecs().add(foodSpec);//一的一方可放弃维护权。
        foodSpec.setFood(food);

        foodDao.save(food);
        foodSpecDao.save(foodSpec);
        return "success!";
    }

    //级联添加
    @GetMapping("/CascadeAdd")
    public void testCascadeAdd()//级联操作
    {
        Food food = new Food();
        food.setFoodName("脆皮鸡饭");

        FoodSpec foodSpec = new FoodSpec();
        foodSpec.setSpecName("辣椒");
        food.getFoodSpecs().add(foodSpec);//一的一方可放弃维护权。
        foodSpec.setFood(food);

        foodDao.save(food);
    }

    //级联删除:  删除一个food的同时，删除该food的所有spec
    @GetMapping("/CascadeDel")
    public void testCascadeDel(@RequestParam("id")long id)//级联操作
    {
        Food food = foodDao.getOne(id);
        foodDao.delete(food);
    }

    //前端上传新Food，后段在数据库保存
    @PostMapping(value = "/postMapping")
    public void testPost(@RequestBody FoodDto foodDto)
    {
        System.out.println(foodDto.toString());
        Food food = foodDto.toEntity();
        //food.getFoodSpecs();
        System.out.println(food.toString());
        foodDao.save(food);
    }
    @GetMapping("/123")
    public void testGet()
    {
        Food food = new Food();
        foodDao.save(food);
    }

}

