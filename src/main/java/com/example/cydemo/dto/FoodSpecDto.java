package com.example.cydemo.dto;


import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.dao.FoodSpecDao;
import com.example.cydemo.entity.FoodSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class FoodSpecDto implements BaseDto{
    private long specId;
    private String specName;
    private float specPrice;
    private int specNum;

    private long foodId;

    public long getSpecId() {
        return specId;
    }

    public void setSpecId(long specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public float getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(float specPrice) {
        this.specPrice = specPrice;
    }

    public int getSpecNum() {
        return specNum;
    }

    public void setSpecNum(int specNum) {
        this.specNum = specNum;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }


    @Autowired
    private FoodDao foodDao;

    public static FoodSpecDto foodSpecDto;
    @PostConstruct
    public void init()
    {
        foodSpecDto = this;
    }

    public FoodSpec toEntity() {
        FoodSpec foodSpec = new FoodSpec();
        foodSpec.setSpecId(this.specId);
        foodSpec.setSpecName(this.specName);
        foodSpec.setSpecPrice(this.specPrice);
        foodSpec.setSpecNum(this.specNum);
        foodSpec.setFood(null);
        foodSpec.setFood(foodSpecDto.foodDao.getOne(this.getFoodId()));
        return foodSpec;
    }
}
