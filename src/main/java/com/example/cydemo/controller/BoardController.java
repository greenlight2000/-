package com.example.cydemo.controller;

import com.example.cydemo.dao.BoardDao;
import com.example.cydemo.dao.RestaurantDao;
import com.example.cydemo.entity.Board;
import com.example.cydemo.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/Board")
public class BoardController {
    @Autowired
    BoardDao boardDao;

    @Autowired
    RestaurantDao restaurantDao;

    //返回该Restaurant厅的所有Board,按时间排序
    public List<Board> ShowAll(Restaurant restaurant,int direction)
    {
        Set<Board> boardSet = restaurantDao.getOne(restaurant.getRestId()).getBoards();
        List<Board> boardList = new ArrayList<Board>(boardSet);
        Collections.sort(boardList, new Comparator<Board>() {

            @Override
            public int compare(Board o1, Board o2) {
                return o1.getBoardTime().compareTo(o2.getBoardTime())*direction;
            }
        });
        for(Board board : boardSet)
        {
            board.setRestaurant(null);
        }
        return boardList;
    }

}
