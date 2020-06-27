package com.example.cydemo.dto;

import com.example.cydemo.dao.RestaurantDao;
import com.example.cydemo.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
@Component
public class RestaurantDto implements BaseDto {
    private long restId;
    private String restName;
    private String building;
    private int floor;
    private String restIntro;

    public long getRestId() {
        return restId;
    }

    public void setRestId(long restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRestIntro() {
        return restIntro;
    }

    public void setRestIntro(String restIntro) {
        this.restIntro = restIntro;
    }
    public RestaurantDto(){}
    public RestaurantDto(long id, String restName, String building, int floor, String restIntro)
    {
        this.restId = id;
        this.restName = restName;
        this.building = building;
        this.floor = floor;
        this.restIntro = restIntro;
    }

    @Autowired
    RestaurantDao restaurantDao;
    public static RestaurantDto restaurantDto;
    @PostConstruct
    public void init()
    {
        restaurantDto=this;
    }
    public Restaurant toEntity()
    {   //餐厅上传业务有两个
        //1.前台选择好要修改的餐厅，body里会添加该餐厅的id，findById查询
        //2.前台选择创建新的餐厅，dto中id自动初始化为0，查询不到，则new一个

        Optional<Restaurant> optRest = restaurantDto.restaurantDao.findById(this.restId);
        Restaurant restaurant = optRest.orElseGet(Restaurant::new);

//      restaurant.setRestId(this.restId);
        restaurant.setRestName(this.restName);
        restaurant.setBuilding(this.building);
        restaurant.setFloor(this.floor);
        restaurant.setRestIntro(this.restIntro);
        return restaurant;
    }
}
