package com.example.cydemo.dto;

import com.example.cydemo.dao.BillDao;
import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.entity.BillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BillOrderDto implements BaseDto {
    private long orderId;
    private float orderPrice;
    private String orderNote;

    private String[] specArr;
//    private FoodDto foodDto;
    private String foodName;
    private String foodPic;
    private long foodId;
    private long billId;

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String[] getSpecArr() {
        return specArr;
    }

    public void setSpecArr(String[] specArr) {
        this.specArr = specArr;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public FoodDao getFoodDao() {
        return foodDao;
    }

    public void setFoodDao(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public BillOrderDto(){}
    public BillOrderDto(long id, float price,String note)
    {
        this.orderId = id;
        this.orderPrice = price;
        this.orderNote = note;
    }

    @Autowired
    FoodDao foodDao;
    @Autowired
    BillDao billDao;

    public static BillOrderDto billOrderDto;
    @PostConstruct
    public void init()
    {
        billOrderDto = this;
    }

    public BillOrder toEntity()
    {
        BillOrder billOrder = new BillOrder(this.orderId,this.orderPrice,this.orderNote);

        //食物配料数组 -> 编码串
        StringBuilder specSeq = new StringBuilder();
        for(String s : this.specArr)
        {
            specSeq.append(s).append(";");
        }
        billOrder.setSpecSeq(specSeq.toString());
        //外键对象匹配：
        //1.匹配数据库中已有的菜品对象
        billOrder.setFood(billOrderDto.foodDao.getOne(this.foodId));
        //2.匹配提前传入数据库的bill对象
        billOrder.setBill(billOrderDto.billDao.getOne(this.billId));
        return billOrder;
    }
}
