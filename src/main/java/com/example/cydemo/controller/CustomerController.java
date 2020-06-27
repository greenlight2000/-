package com.example.cydemo.controller;

import com.example.cydemo.dao.CustomerDao;
import com.example.cydemo.dto.CustomerDto;
import com.example.cydemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Customer")
public class CustomerController extends BaseController {
    @Autowired
    CustomerDao customerDao;

    @PostMapping("/submit")
    public void submitCustomer(@RequestBody CustomerDto customerDto)
    {
        Customer customer = customerDto.toEntity();
        customerDao.save(customer);
    }



    @GetMapping("/mails")
    public void getMails()
    {
        ;
    }
    @GetMapping("/posts")
    public void getPosts()
    {
        ;
    }
    @GetMapping("/bills")
    public void getBills()
    {
        ;
    }
    @GetMapping("/comments")
    public void getComments()
    {
        ;
    }
    @GetMapping("/replys")
    public void getReplys()
    {
        ;
    }
}
