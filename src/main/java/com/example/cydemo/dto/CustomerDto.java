package com.example.cydemo.dto;

import com.example.cydemo.dao.CustomerDao;
import com.example.cydemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class CustomerDto {
    private long customerId;
    private String customerName;
    private String custPic;

    private String customerSex;
    private String identity;
    private String university;
    private String school;
    private String hometown;
    private String customerIntro;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustPic() {
        return custPic;
    }

    public void setCustPic(String custPic) {
        this.custPic = custPic;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getCustomerIntro() {
        return customerIntro;
    }

    public void setCustomerIntro(String customerIntro) {
        this.customerIntro = customerIntro;
    }

    @Autowired
    CustomerDao customerDao;

    public static CustomerDto customerDto;
    @PostConstruct
    public void init()
    {
        customerDto = this;
    }
    public Customer toEntity()
    {
        Optional<Customer> optCust = customerDto.customerDao.findById(this.customerId);
        Customer customer = optCust.orElseGet(Customer::new);

        customer.setCustomerName(this.customerName);
        customer.setCustomerSex(this.customerSex);
        customer.setIdentity(this.identity);
        customer.setUniversity(this.university);
        customer.setSchool(this.school);
        customer.setHometown(this.hometown);
        customer.setCustomerIntro(this.customerIntro);
        customer.setCustPic(this.custPic);

        return customer;
    }
}
