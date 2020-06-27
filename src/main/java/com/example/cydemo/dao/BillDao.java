package com.example.cydemo.dao;

import com.example.cydemo.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BillDao extends BaseDao<Bill> {
}
