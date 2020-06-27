package com.example.cydemo.dao;

import com.example.cydemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostDao extends JpaRepository<Post,Long> , JpaSpecificationExecutor<Post> {
}
