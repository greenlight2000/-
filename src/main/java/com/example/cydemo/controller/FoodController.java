package com.example.cydemo.controller;

import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.dao.FoodSpecDao;
import com.example.cydemo.dao.RestaurantDao;
import com.example.cydemo.dto.FoodDto;
import com.example.cydemo.dto.FoodSpecDto;
import com.example.cydemo.entity.Food;
import com.example.cydemo.entity.FoodSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Food")
public class FoodController extends BaseController<Food> {

    FoodDao foodDao;
    @Autowired
    public FoodController(FoodDao foodDao)
    {
        this.foodDao = foodDao;
        init(this.foodDao);
    }
    //上传菜品时 菜品和配料异步提交（先存入菜品生成id，再把id放入配料dto存入相应菜品）
    @PostMapping("/submit")
    public long submitFood(@RequestBody FoodDto foodDto)
    {
        Food food = foodDto.toEntity();
        foodDao.save(food);

        return food.getFoodId();
    }
    @PostMapping("/addOneSpec")
    public FoodDto addOneSpec(@RequestBody FoodSpecDto foodSpecDto)
    {
        long foodId = foodSpecDto.getFoodId();
        Food food = foodDao.getOne(foodId);
        Set<FoodSpec> foodSpecSet = food.getFoodSpecs();
        foodSpecSet.add(foodSpecDto.toEntity());
        food.setFoodSpecs(foodSpecSet);
        foodDao.save(food);

        return food.toDto();
    }
    @PostMapping("/addSpec")
    public void addSpec(@RequestBody Set<FoodSpecDto> foodSpecDtoSet)
    {
        Food food = foodDao.getOne(53L);

        Set<FoodSpec> foodSpecSet = new HashSet<>();
        for(FoodSpecDto foodSpecDto : foodSpecDtoSet)
        {
            foodSpecSet.add(foodSpecDto.toEntity());
        }
        food.setFoodSpecs(foodSpecSet);

        foodDao.save(food);
    }
    /**
     * 通过传参自定义搜索food
     * @param key
     * @param value
     * @param isFuzzy
     * @return
     */
    @GetMapping("/searchFood")
    public List<FoodDto> searchFood(@RequestParam("key")String key, @RequestParam("value")String value, @RequestParam("isFuzzy")boolean isFuzzy)
    {
        List<Food> entityList = super.searchTs(key,value,isFuzzy);
        List<FoodDto> dtoList = new ArrayList<>();
        //entity -> dto
        for(Food f : entityList)
        {
            dtoList.add(f.toDto());
        }
        return dtoList;
    }

    /**
     * 查询数据库中所有food
     * @return （page）
     */
    @GetMapping("/findAll")
    public PageImpl<FoodDto> findAllFood()
    {
        Page<Food> entityPage = foodDao.findAll(PageRequest.of(0,3));
        List<Food> entityList = foodDao.findAll();
        List<FoodDto> dtoList = new ArrayList<>();
        for(Food food : entityList)
        {
            dtoList.add(food.toDto());
        }
//        PageImpl<FoodDto> dtoPage = new PageImpl<FoodDto>(dtoList,PageRequest.of(0,3),dtoList.size());
//        System.out.println("字段数"+dtoPage.getTotalElements());
//        System.out.println("页数"+dtoPage.getTotalPages());
//        System.out.println(dtoPage.getContent().size());
//        System.out.println(dtoPage.getContent().get(3));
//        System.out.println("hasNext()"+dtoPage.hasNext());
        //System.out.println(entityPage.getContent());
        List<FoodDto> foodDtoList = new ArrayList<>();
        for(Food food : entityPage.getContent())
        {
            foodDtoList.add(food.toDto());
        }
        PageImpl<FoodDto> dtoPage = new PageImpl<FoodDto>(foodDtoList,PageRequest.of(0,3),dtoList.size());
        System.out.println(entityPage.getContent().size());

        return dtoPage;

    }


}
