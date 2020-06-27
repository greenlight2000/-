package com.example.cydemo.entity;

import com.example.cydemo.dto.BillDto;
import com.example.cydemo.dto.BillOrderDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bill")
public class Bill implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private long billId;
    @Column(name = "pay_time")
    private Timestamp payTime;
    @Column(name = "take_time")
    private Timestamp takeTime;
    @Column(name = "pay_status")
    private String payStatus;
    @Column(name = "bill_num")
    private String billNum;
    @Column(name = "total_price")
    private float billPrice;



    //Bill <-- BillOrder外键
    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    private Set<BillOrder> billOrders = new HashSet<>();

    //customer <-- bill外键
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",referencedColumnName = "cust_id")
    private Customer customer;

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }


    public Set<BillOrder> getBillOrders() {
        return billOrders;
    }

    public void setBillOrders(Set<BillOrder> billOrders) {
        this.billOrders = billOrders;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public float getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(float billPrice) {
        this.billPrice = billPrice;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Timestamp takeTime) {
        this.takeTime = takeTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getBillNum() {
        return billNum;
    }

    public void setListNum(String billNum) {
        this.billNum = billNum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", payTime=" + payTime +
                ", takeTime=" + takeTime +
                ", payStatus='" + payStatus + '\'' +
                ", billNum='" + billNum + '\'' +
                ", billPrice=" + billPrice +
                ", billOrders=" + billOrders +
                ", customer=" + customer +
                '}';
    }
    public Bill(){}
    public Bill(long id, Timestamp payTime, Timestamp takeTime, String payStatus, String billNum, float billPrice)
    {
        this.billId = id;
        this.payTime = payTime;
        this.takeTime = takeTime;
        this.payStatus = payStatus;
        this.billNum = billNum;
        this.billPrice = billPrice;
    }
    public BillDto toDto()
    {
        BillDto billDto = new BillDto(this.billId,this.payTime,this.takeTime,this.payStatus,this.billNum,this.billPrice);

        billDto.setCustomerId(this.customer.getCustomerId());

        Set<BillOrderDto> billOrderDtoSet = new HashSet<>();
        for(BillOrder billOrder : this.getBillOrders())
        {
            billOrderDtoSet.add(billOrder.toDto());
        }
        billDto.setBillOrderDtoSet(billOrderDtoSet);

        return billDto;
    }
}
