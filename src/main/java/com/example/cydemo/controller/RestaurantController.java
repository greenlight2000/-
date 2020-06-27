package com.example.cydemo.controller;

import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.dao.LabelDao;
import com.example.cydemo.dao.RestaurantDao;
import com.example.cydemo.dto.FoodDto;
import com.example.cydemo.dto.RestaurantDto;
import com.example.cydemo.entity.Food;
import com.example.cydemo.entity.FoodSpec;
import com.example.cydemo.entity.Label;
import com.example.cydemo.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/Restaurant")
public class RestaurantController extends BaseController<Restaurant>{

    RestaurantDao restaurantDao;
    @Autowired
    public RestaurantController(RestaurantDao restaurantDao)
    {
        this.restaurantDao = restaurantDao;
        init(this.restaurantDao);
    }

    @Autowired
    FoodDao foodDao;
    @Autowired
    LabelDao labelDao;

    @GetMapping("/searchRestaurant")
    public List<RestaurantDto> searchRestaurant(@RequestParam("key")String key, @RequestParam("value")String value, @RequestParam("isFuzzy")boolean isFuzzy)
    {
        List<Restaurant> entityList = super.searchTs(key,value,isFuzzy);
        List<RestaurantDto> dtoList = new ArrayList<>();
        //entity -> dto
        for(Restaurant r : entityList)
        {
            dtoList.add(r.toDto());
        }
        return dtoList;
    }

    /**
     * 上传餐厅（1.新建餐厅 2.修改餐厅资料）
     * @param restaurantDto
     */
    @PostMapping("/submit")
    public void SubmitRestaurant(@RequestBody RestaurantDto restaurantDto)
    {//直接无脑转entity，判断是否需要创建新的被封装Restaurant方法进了toEntity()
        Restaurant restaurant = restaurantDto.toEntity();
        restaurantDao.save(restaurant);
    }
    @GetMapping("/getRestInfo")
    public RestaurantDto getRestInfo(@RequestParam("restaurantId") long restaurantId)
    {
        return restaurantDao.getOne(restaurantId).toDto();
    }

    /**
     * 返回该Restaurant的所有Food  根据 时间，热度（订单数）排序
     * @param restaurantId
     * @param orderParam "价格"/"热度"
     * @param direction
     * @return
     */
    @GetMapping("/getFoodByRest")
    public List<FoodDto> getFoodsByRest(@RequestParam("restaurantId") long restaurantId,@RequestParam("orderParam") String orderParam,@RequestParam("direction") int direction)
    {
        Restaurant restaurant = restaurantDao.getOne(restaurantId);
        Set<Food> foodSet = restaurant.getFoods();
        List<Food> foodList = new ArrayList<>(foodSet);
        Collections.sort(foodList, new Comparator<Food>()
        {
            @Override
            public int compare(Food o1, Food o2)
            {
                int r = 0;
                if(orderParam.equals("价格"))
                    r = (o1.getFoodPrice()-o2.getFoodPrice()>0)? direction:(-1) * direction;
                else if(orderParam.equals("热度"))
                    r = o1.getBillOrders().size()-o2.getBillOrders().size();

                return r;
            }
        });
        List<FoodDto> foodDtoList = new ArrayList<>();
        for(Food food : foodList)
        {
            foodDtoList.add(food.toDto());
        }
        return foodDtoList;
    }



}
