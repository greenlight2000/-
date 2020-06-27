package com.example.cydemo.dao;


import com.example.cydemo.entity.FoodSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FoodSpecDao extends BaseDao<FoodSpec> {
}
