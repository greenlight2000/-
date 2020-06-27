package com.example.cydemo.entity;

import com.example.cydemo.dto.FoodDto;
import com.example.cydemo.dto.FoodSpecDto;
import com.example.cydemo.dto.LabelDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "food")
public class Food implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private long  foodId;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "price")
    private float foodPrice;
    @Column(name = "picurl")
    private String foodPic;
    @Column(name = "intro")
    private String foodIntro;
    @Column(name = "status")
    private String foodStatus;
    @Column(name = "window_intro")
    private String windowIntro;
    @Column(name = "likes")
    private int foodLikes;
    @Column(name = "food_num")
    private int foodNum;

    @ManyToMany(targetEntity = Label.class,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "sys_food_label",
        //当前对象在中间表中的外键
        joinColumns = {@JoinColumn(name = "food_id")},

        //对方对象在中间表的外键
        inverseJoinColumns = {@JoinColumn(name = "label_id")}
    )
    private Set<Label> labels = new HashSet<Label>(0);

    //Food <-- FoodSpec外键
              //mappedBy = "从表中的外键对象属性"
    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
    private Set<FoodSpec> foodSpecs = new HashSet<>();
    //Food <-- Order外键
//    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
//    private Set<Order> orders = new HashSet<>();
    //Food <-- Comment外键
    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
    //Food <-- BillOrder外键
    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
    private Set<BillOrder> billOrders = new HashSet<>();

    //Restaurant <-- Food外键
    @ManyToOne(targetEntity = Restaurant.class)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "rest_id")
    private Restaurant restaurant;

    public Set<BillOrder> getBillOrders() {
        return billOrders;
    }

    public void setBillOrders(Set<BillOrder> billOrders) {
        this.billOrders = billOrders;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Set<FoodSpec> getFoodSpecs() {
        return foodSpecs;
    }

    public void setFoodSpecs(Set<FoodSpec> foodSpecs) {
        this.foodSpecs = foodSpecs;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodPic='" + foodPic + '\'' +
                ", foodIntro='" + foodIntro + '\'' +
                ", foodStatus='" + foodStatus + '\'' +
                ", windowIntro='" + windowIntro + '\'' +
                ", foodLikes=" + foodLikes +
                ", foodNum=" + foodNum +
                ", labels=" + labels +
                ", foodSpecs=" + foodSpecs +
                ", comments=" + comments +
                ", billOrders=" + billOrders +
                ", restaurant=" + restaurant +
                '}';
    }
    public Food() { }
    public Food(FoodDto foodDto) {
        this.foodId = foodDto.getFoodId();
        this.foodName = foodDto.getFoodName();
        this.foodPrice = foodDto.getFoodPrice();
        this.foodPic = foodDto.getFoodPic();
        this.foodIntro = foodDto.getFoodIntro();
        this.foodStatus = foodDto.getFoodStatus();
        this.windowIntro = foodDto.getWindowIntro();
        this.foodLikes = foodDto.getFoodLikes();
        this.foodNum = foodDto.getFoodNum();
        this.setRestaurant(null);
        this.setFoodSpecs(null);
        this.setComments(null);
        this.setBillOrders(null);
        this.setLabels(null);
    }
    public Food(FoodDto foodDto, Restaurant restaurant, Set<FoodSpec> foodSpecs, Set<Comment> comments, Set<BillOrder> billOrders, Set<Label> labels) {
        this.foodId = foodDto.getFoodId();
        this.foodName = foodDto.getFoodName();
        this.foodPrice = foodDto.getFoodPrice();
        this.foodPic = foodDto.getFoodPic();
        this.foodIntro = foodDto.getFoodIntro();
        this.foodStatus = foodDto.getFoodStatus();
        this.windowIntro = foodDto.getWindowIntro();
        this.foodLikes = foodDto.getFoodLikes();
        this.foodNum = foodDto.getFoodNum();
        this.setRestaurant(restaurant);
        this.setFoodSpecs(foodSpecs);
        this.setComments(comments);
        this.setBillOrders(billOrders);
        this.setLabels(labels);
    }

    @Override
    //Food -> FoodData  :  FoodData foodData = food.toFoodData
    public FoodDto toDto()
    {
        FoodDto foodDto = new FoodDto();
        foodDto.setFoodId(this.foodId);
        foodDto.setFoodName(this.foodName);
        foodDto.setFoodPrice(this.foodPrice);
        foodDto.setFoodPic(this.foodPic);
        foodDto.setFoodIntro(this.foodIntro);
        foodDto.setFoodStatus(this.foodStatus);
        foodDto.setWindowIntro(this.windowIntro);
        foodDto.setFoodLikes(this.foodLikes);
        foodDto.setFoodNum(this.foodNum);
        foodDto.setRestaurantId(this.getRestaurant().getRestId());

        Set<LabelDto> labelDtoSet = new HashSet<>();
        for(Label label : this.getLabels())
        {
            labelDtoSet.add(label.toDto());
        }
        foodDto.setLabelDtoSet(labelDtoSet);

        Set<FoodSpecDto> foodSpecDtoSet = new HashSet<>();
        for(FoodSpec foodSpec : this.getFoodSpecs())
        {
            foodSpecDtoSet.add(foodSpec.toDto());
        }
        foodDto.setFoodSpecDtoSet(foodSpecDtoSet);

        return foodDto;
    }

    public FoodDto tosFieldDto()
    {
        FoodDto foodDto = new FoodDto();
        foodDto.setFoodId(this.foodId);
        foodDto.setFoodName(this.foodName);
        foodDto.setFoodPrice(this.foodPrice);
        foodDto.setFoodPic(this.foodPic);
        return foodDto;
    }
}

