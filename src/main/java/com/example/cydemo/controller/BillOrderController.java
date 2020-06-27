package com.example.cydemo.controller;

import com.example.cydemo.dao.BillOrderDao;
import com.example.cydemo.dto.BillOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BillOrder")
public class BillOrderController {
    @Autowired
    BillOrderDao billOrderDao;

    @PostMapping("/submitBillOrder")
    public void submitOrder(@RequestBody BillOrderDto billOrderDto)
    {
        billOrderDao.save(billOrderDto.toEntity());
    }
}
