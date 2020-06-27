package com.example.cydemo.dao;

import com.example.cydemo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BoardDao extends JpaRepository<Board,Long>, JpaSpecificationExecutor<Board> {
}
