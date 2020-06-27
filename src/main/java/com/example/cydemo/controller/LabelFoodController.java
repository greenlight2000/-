package com.example.cydemo.controller;

import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.dao.LabelDao;
import com.example.cydemo.dao.RestaurantDao;
import com.example.cydemo.dto.FoodDto;
import com.example.cydemo.dto.LabelDto;
import com.example.cydemo.entity.Food;
import com.example.cydemo.entity.Label;
import com.example.cydemo.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Label")
public class LabelFoodController {
    @Autowired
    FoodDao foodDao;
    @Autowired
    LabelDao labelDao;


    /**
     * 根据Label查询Food    根据 时间，热度（订单数）排序
     * @param labelId
     * @param orderParam  "价格"/"热度"
     * @param direction
     * @return
     */
    @GetMapping("/getFoodsByLabel")
    public List<FoodDto> getFoodsByLabel(@RequestParam("labelId") long labelId, @RequestParam("orderParam") String orderParam, @RequestParam("direction") int direction)
    {
        Label label = labelDao.getOne(labelId);
        Set<Food> foodSet = label.getFoods();
        List<Food> foodList = new ArrayList<>(foodSet);
        Collections.sort(foodList, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
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
    @Autowired
    RestaurantDao restaurantDao;
    @GetMapping("/ManyToMany")
    public String testCascadeAdd()
    {
        Food food = new Food();
        food.setFoodName("牛肉水饺");
        food.setFoodPrice(14);

        Label label1 = new Label();
        label1.setLabelName("面食");
        Label label2 = new Label();
        label2.setLabelName("饺子");

        food.getLabels().add(label1);
        food.getLabels().add(label2);
        //jointColumn/jointTable在谁那，谁维护
        foodDao.save(food);
        return "success insert";
    }
    @GetMapping("/ManyToMany2")
    public String testCascadeDel(@RequestParam("id")Long id)
    {
        Food food = foodDao.getOne(id);
        foodDao.delete(food);
        return "success delete";
    }
//    @GetMapping("/getLabels")
//    public StringBuilder getLabels(@RequestParam("id")Long id)
//    {
//        Food food = foodDao.getOne(id);
//        Set<Label> labelSet = food.getLabels();
//        StringBuilder s = new StringBuilder();
//        for(Label label:labelSet)
//        {
//            s.append(label.getLabelName().toString());
//            System.out.println(label.getLabelName());
//        }
//        return s;
//    }
    @GetMapping("/getAllLabels")
    public Set<LabelDto> getAllLabels()
    {
        List<Label> labelList = labelDao.findAll();
        Set<LabelDto> labelDtoSet = new HashSet<>();

        for(Label label : labelList)
        {
            labelDtoSet.add(label.toDto());
        }
        return labelDtoSet;
    }
    //新增label
    @PostMapping("/submitLabel")
    public void submitLabel(@RequestBody LabelDto labelDto)
    {
        labelDao.save(labelDto.toEntity());
    }
    @PostMapping("/bindFoodLabels")
    public void bindFoodLabels(@RequestParam("labelIdString")String labelIdString,/*@RequestParam(value = "labelIdList")List<Long> labelIdList,*/ @RequestParam("foodId")long foodId)
    {
        String[] stringList = labelIdString.split(",");
        List<Long> labelIdList = new ArrayList<>();
        for(String s : stringList)
        {
            labelIdList.add(Long.parseLong(s));
        }
//        System.out.println(Arrays.toString(stringList));
//        System.out.println(labelIdList);
        System.out.println(labelIdList);
        System.out.println(foodId);

        Food food = foodDao.getOne(foodId);
        Set<Label> labelSet = food.getLabels();
        for(Long labelId : labelIdList)
        {
            Label label = labelDao.getOne(labelId);
            labelSet.add(label);
        }
        food.setLabels(labelSet);
        foodDao.save(food);

    }
    @GetMapping("/clearLabel")
    public void clearFoodLabels(@RequestParam("foodId")long foodId)
    {
        Food food = foodDao.getOne(foodId);
        food.getLabels().clear();
        foodDao.save(food);
    }


}
