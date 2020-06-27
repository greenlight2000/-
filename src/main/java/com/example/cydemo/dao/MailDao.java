package com.example.cydemo.dao;

import com.example.cydemo.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MailDao extends JpaRepository<Mail,Long> , JpaSpecificationExecutor<Mail> {
}
