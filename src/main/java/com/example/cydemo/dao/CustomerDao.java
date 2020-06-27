package com.example.cydemo.dao;

import com.example.cydemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerDao extends BaseDao<Customer> {
}
