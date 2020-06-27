package com.example.cydemo.controller;


import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.dao.FoodSpecDao;
import com.example.cydemo.entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import com.example.cydemo.entity.FoodSpec;

import javax.persistence.criteria.*;
import java.util.*;

@RestController
@RequestMapping("/FoodSpec")
public class  FoodSpecController
{
    @Autowired
    FoodSpecDao foodSpecDao;

    @Autowired
    FoodDao foodDao;

    //由于实体类中存在外键属性，此操作无限套娃
    @GetMapping("/findAll")
    public List<FoodSpec> findAll()
    {
        List<FoodSpec> all = foodSpecDao.findAll(Sort.by(Sort.Direction.ASC,"specId"));
        for(FoodSpec foodSpec : all){
            foodSpec.setFood(null);
        }
        System.out.println("there"+all.toString()+"there");
        return all;
    }
    //1
    @GetMapping("/SearchEqual")
    public List findSpec(@RequestParam("key")String key,@RequestParam("value")String value)//@RequestParam()里的参数键名要和url中的参数键名一致
    {

        //（内部类） 定制检索信息，获得一个Specification实例
        Specification<FoodSpec> spec = new Specification<FoodSpec>()
        {
            // 构造查询谓词条件，返回一个Predicate实例
            @Override
            public Predicate toPredicate(Root<FoodSpec> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                //select * from FoodSpec where spec_name = ?
                Predicate equal = criteriaBuilder.equal(root.get(key), value);
                //select * from FoodSpec where spec_name like ?
                Predicate like = criteriaBuilder.like(root.get(key).as(String.class),"%"+value+"%");
                return like;
            }
        };
        //用构造好的 spec信息 放入接口，进行查询
        List<FoodSpec> list = foodSpecDao.findAll(spec, Sort.by(Sort.Direction.DESC,"specId"));
        for(FoodSpec foodSpec:list)
        {
            System.out.println(foodSpec.getSpecName());
            foodSpec.setFood(null);
        }
        return list;
    }
    //返回该Restaurant厅的所有Board,按时间排序
    @GetMapping("/test")                                     //direction = 1:ASC -1:DESC
    public List<FoodSpec> ShowAll(@RequestParam("id")long id,@RequestParam("direction")int direction)
    {
        Food food = foodDao.getOne(id);

        Set<FoodSpec> foodSpecSet = foodDao.getOne(food.getFoodId()).getFoodSpecs();
        List<FoodSpec> foodSpecList = new ArrayList<FoodSpec>(foodSpecSet);
        Collections.sort(foodSpecList, new Comparator<FoodSpec>()
        {
            @Override
            public int compare(FoodSpec o1, FoodSpec o2)
            {
                return (o1.getSpecPrice() > o2.getSpecPrice()) ? direction : (-1) * direction;
            }
        });
        for(FoodSpec foodSpec : foodSpecList)
        {
            foodSpec.setFood(null);
        }
        return foodSpecList;
    }

}
