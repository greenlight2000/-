package com.example.cydemo.dao;

import com.example.cydemo.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LabelDao extends JpaRepository<Label,Long>, JpaSpecificationExecutor<Label> {
}
