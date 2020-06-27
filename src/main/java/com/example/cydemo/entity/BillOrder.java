package com.example.cydemo.entity;

import com.example.cydemo.dto.BaseDto;
import com.example.cydemo.dto.BillOrderDto;

import javax.persistence.*;

@Entity
@Table(name = "bill_order")//order好像是个关键字，用的话过不了编译（sql语句出错）
public class BillOrder implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_order_id")
    private long orderId;
    @Column(name = "bill_order_price")
    private float orderPrice;
    @Column(name = "spec_seq")
    private String specSeq;
    @Column(name = "bill_order_note")
    private String orderNote;

    //Bill <-- BillOrder外键
    @ManyToOne(targetEntity = Bill.class)
    @JoinColumn(name = "bill_id",referencedColumnName = "bill_id")
    private Bill bill;
    //Food <-- BillOrder外键
    @ManyToOne(targetEntity = Food.class)
    @JoinColumn(name = "food_id",referencedColumnName = "food_id")
    private Food food;

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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

    public String getSpecSeq() {
        return specSeq;
    }

    public void setSpecSeq(String specSeq) {
        this.specSeq = specSeq;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    @Override
    public String toString() {
        return "BillOrder{" +
                "orderId=" + orderId +
                ", orderPrice=" + orderPrice +
                ", specSeq='" + specSeq + '\'' +
                ", orderNote='" + orderNote + '\'' +
                ", bill=" + bill +
                ", food=" + food +
                '}';
    }

    public BillOrder(){}
    public BillOrder(long id, float price,String note)
    {
        this.orderId = id;
        this.orderPrice = price;
        this.orderNote = note;
    }
    @Override
    public BillOrderDto toDto()
    {
        BillOrderDto billOrderDto = new BillOrderDto(this.orderId,this.orderPrice,this.orderNote);
        //bill Id
        billOrderDto.setBillId(this.bill.getBillId());
        //菜品基本信息
        billOrderDto.setFoodId(this.food.getFoodId());
        billOrderDto.setFoodName(this.food.getFoodName());
        billOrderDto.setFoodPic(this.food.getFoodPic());
        //配料串->配料数组  (正则表达式查询用';'分割的配料)
        billOrderDto.setSpecArr(this.specSeq.split("\\;"));

        return billOrderDto;
    }
}
