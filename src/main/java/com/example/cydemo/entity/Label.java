package com.example.cydemo.entity;

import com.example.cydemo.dto.BaseDto;
import com.example.cydemo.dto.FoodDto;
import com.example.cydemo.dto.LabelDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "label")
public class Label implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private long labelId;
    @Column(name = "name")
    private String labelName;

    @ManyToMany(mappedBy = "labels",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Food> foods = new HashSet<Food>();

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

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", foods=" + foods +
                '}';
    }
    public LabelDto toDto()
    {
        LabelDto labelDto = new LabelDto();

        labelDto.setLabelId(this.labelId);
        labelDto.setLabelName(this.labelName);

        Set<Long> foodIdSet = new HashSet<>();
        for(Food food : this.foods)
        {
            foodIdSet.add(food.getFoodId());
        }

        labelDto.setFoodIdSet(foodIdSet);
        return labelDto;
    }
}
