package com.example.cydemo.dto;

import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.dao.FoodSpecDao;
import com.example.cydemo.dao.RestaurantDao;
import com.example.cydemo.entity.Food;
import com.example.cydemo.entity.FoodSpec;
import com.example.cydemo.entity.Label;
import com.example.cydemo.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Component
public class FoodDto implements BaseDto{

    private long  foodId;
    private String foodName;
    private float foodPrice;
    private String foodPic;
    private String foodIntro;
    private String foodStatus;
    private String windowIntro;
    private int foodLikes;
    private int foodNum;
    private long restaurantId;
    private Set<FoodSpecDto> foodSpecDtoSet;
    private Set<LabelDto> labelDtoSet = new HashSet<>();//foodDto里面存labelDto实例，labelDto里存foodId
    /**
     * 这两个属性另外写函数查询，不以dto形式打包传输
     */
//    private Set<Comment> comments = new HashSet<>();
//    private Set<BillOrder> billOrders = new HashSet<>();


    public Set<LabelDto> getLabelDtoSet() {
        return labelDtoSet;
    }

    public void setLabelDtoSet(Set<LabelDto> labelDtoSet) {
        this.labelDtoSet = labelDtoSet;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }

    public String getFoodIntro() {
        return foodIntro;
    }

    public void setFoodIntro(String foodIntro) {
        this.foodIntro = foodIntro;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }

    public String getWindowIntro() {
        return windowIntro;
    }

    public void setWindowIntro(String windowIntro) {
        this.windowIntro = windowIntro;
    }

    public int getFoodLikes() {
        return foodLikes;
    }

    public void setFoodLikes(int foodLikes) {
        this.foodLikes = foodLikes;
    }

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public Set<FoodSpecDto> getFoodSpecDtoSet() {
        return foodSpecDtoSet;
    }

    public void setFoodSpecDtoSet(Set<FoodSpecDto> foodSpecDtoSet) {
        this.foodSpecDtoSet = foodSpecDtoSet;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "FoodData{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodPic='" + foodPic + '\'' +
                ", foodIntro='" + foodIntro + '\'' +
                ", foodStatus='" + foodStatus + '\'' +
                ", windowIntro='" + windowIntro + '\'' +
                ", foodLikes=" + foodLikes +
                ", foodNum=" + foodNum +
                ", restaurantId=" + restaurantId +
                '}';
    }
    //@RequestBody映射的实体类不能有含参构造函数


    @Autowired
    private FoodDao foodDao;
    @Autowired
    private RestaurantDao restaurantDao;

    public static FoodDto foodDto;
    @PostConstruct
    public void init()
    {
        foodDto = this;
    }
    @Override
    public Food toEntity()
    {
        //如果前台要修改菜品，则传回一个存在的foodId，在这里findById()获取该food对象进行修改
        //如果前台要新添加菜品，则传回foodId=0，数据库搜索不到，则new一个Food
        Optional<Food> optFood = foodDto.foodDao.findById(this.foodId);
        Food food = optFood.orElseGet(Food::new);

        food.setFoodId(this.foodId);
        food.setFoodName(this.foodName);
        food.setFoodPrice(this.foodPrice);
        food.setFoodPic(this.foodPic);
        food.setFoodIntro(this.foodIntro);
        food.setFoodStatus(this.foodStatus);
        food.setWindowIntro(this.windowIntro);
        food.setFoodLikes(this.foodLikes);
        food.setFoodNum(this.foodNum);
        food.setRestaurant(foodDto.restaurantDao.getOne(this.getRestaurantId()));

        //如果不含数据，dto传过来的数组必须为空 而不是null，所以不用担心报错
        Set<FoodSpec> foodSpecSet = new HashSet<>();
        for(FoodSpecDto foodSpecDto : this.foodSpecDtoSet)
        {
            FoodSpec foodSpec = foodSpecDto.toEntity();
            foodSpecSet.add(foodSpec);
        }
        food.setFoodSpecs(foodSpecSet);

        Set<Label> labelSet = new HashSet<>();
        for(LabelDto labelDto : this.labelDtoSet)
        {
            Label label = labelDto.toEntity();
            labelSet.add(label);
        }
        food.setLabels(labelSet);

        return food;
    }
}

