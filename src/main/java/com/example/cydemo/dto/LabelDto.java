package com.example.cydemo.dto;

import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.entity.Food;
import com.example.cydemo.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class LabelDto implements BaseDto{
    private long labelId;
    private String labelName;
    private Set<Long> foodIdSet = new HashSet<>();//foodDto里面存labelDto实例，labelDto里存foodId

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Set<Long> getFoodIdSet() {
        return foodIdSet;
    }

    public void setFoodIdSet(Set<Long> foodSet) {
        this.foodIdSet = foodSet;
    }

    @Override
    public String toString() {
        return "LabelDto{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", foodSet=" + foodIdSet +
                '}';
    }

    @Autowired
    FoodDao foodDao;

    public static LabelDto labelDto;
    @PostConstruct
    public void init() { labelDto = this;}

    @Override
    public Label toEntity()
    {
        Label label = new Label();
        label.setLabelId(this.labelId);
        label.setLabelName(this.labelName);

        Set<Food> foodSet = new HashSet<>();

        for(long id : this.foodIdSet)
        {
            foodSet.add(labelDto.foodDao.getOne(id));
        }

        label.setFoods(foodSet);
        return label;
    }
}
