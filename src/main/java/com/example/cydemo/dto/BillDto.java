package com.example.cydemo.dto;

import com.example.cydemo.dao.CustomerDao;
import com.example.cydemo.entity.Bill;
import com.example.cydemo.entity.BillOrder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class BillDto implements BaseDto{
    private long billId;
    private Timestamp payTime;
    private Timestamp takeTime;
    private String payStatus;
    private String billNum;
    private float billPrice;

    private long customerId;
    private Set<BillOrderDto> billOrderDtoSet = new HashSet<>();

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
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

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public float getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(float billPrice) {
        this.billPrice = billPrice;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Set<BillOrderDto> getBillOrderDtoSet() {
        return billOrderDtoSet;
    }

    public void setBillOrderDtoSet(Set<BillOrderDto> billOrderDtoSet) {
        this.billOrderDtoSet = billOrderDtoSet;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public BillDto(){}
    public BillDto(long id, Timestamp payTime, Timestamp takeTime, String payStatus, String billNum, float billPrice)
    {
        this.billId = id;
        this.payTime = payTime;
        this.takeTime = takeTime;
        this.payStatus = payStatus;
        this.billNum = billNum;
        this.billPrice = billPrice;
    }

    @Autowired
    CustomerDao customerDao;

    public static BillDto billDto;
    @PostConstruct
    public void init(){billDto = this;}

    public Bill toEntity()
    {
        Bill bill = new Bill(this.billId,this.payTime,this.takeTime,this.payStatus,this.billNum,this.billPrice);

        bill.setCustomer(billDto.customerDao.getOne(this.customerId));

        Set<BillOrder> billOrderSet = new HashSet<>();
        for(BillOrderDto billOrderDto : this.billOrderDtoSet)
        {
            billOrderSet.add(billOrderDto.toEntity());
        }
        bill.setBillOrders(billOrderSet);
        return bill;
    }

}
