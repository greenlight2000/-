package com.example.cydemo.dao;

import com.example.cydemo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentDao extends JpaRepository<Comment,Long>, JpaSpecificationExecutor<Comment> {
}
