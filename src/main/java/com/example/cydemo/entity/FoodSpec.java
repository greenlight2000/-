package com.example.cydemo.entity;

import com.example.cydemo.dto.FoodSpecDto;

import javax.persistence.*;

@Entity
@Table(name = "food_specification")
public class FoodSpec implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_spec_id")
    private long specId;
    @Column(name = "food_spec_name")
    private String specName;
    @Column(name = "food_spec_price")
    private float specPrice;
    @Column(name = "food_spec_num")
    private int specNum;

    //Food <-- FoodSpec外键
    @ManyToOne(targetEntity = Food.class)
    @JoinColumn(name = "food_id",referencedColumnName = "food_id")
    private Food food;//对应mappedBy

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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "FoodSpec{" +
                "specId=" + specId +
                ", specName='" + specName + '\'' +
                ", specPrice=" + specPrice +
                ", specNum=" + specNum +
                ", food=" + food +
                '}';
    }

    public FoodSpecDto toDto() {
        FoodSpecDto foodSpecDto = new FoodSpecDto();
        foodSpecDto.setSpecId(this.specId);
        foodSpecDto.setSpecName(this.specName);
        foodSpecDto.setSpecPrice(this.specPrice);
        foodSpecDto.setSpecNum(this.specNum);
        foodSpecDto.setFoodId(this.getFood().getFoodId());
        return foodSpecDto;
    }
}
