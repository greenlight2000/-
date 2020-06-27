package com.example.cydemo.dao;

import com.example.cydemo.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FoodDao extends BaseDao<Food> {

}
