package com.example.cydemo.controller;

import com.example.cydemo.dao.CommentDao;
import com.example.cydemo.dao.CustomerDao;
import com.example.cydemo.dao.FoodDao;
import com.example.cydemo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;


@RestController
@RequestMapping("/Comment")
public class CommentController {
    @Autowired
    CommentDao commentDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    FoodDao foodDao;

    //保存新添评论
    public void saveComm(Comment replyComment,Food replyFood,Customer customer,String text,Timestamp time)
    {
        Comment comment = new Comment();
        /** 1.如果replyComment == null  */
        if(replyComment == null)
        {
            comment.setFood(replyFood);
            comment.setCustomer(customer);
            comment.setToWhom(null);
            comment.setParentComm(null);
            comment.setChildrenComm(null);
            comment.setCommTime(time);
            comment.setCommText(text);
            comment.setCommLikes(0);
        /** 2.如果replyComment != null  */
        }else if(replyComment != null)
        {
            //  2.1 如果replyComment 是parent
            replyComment.setChildrenComm(null);
            replyComment.setParentComm(null);
            replyComment.setToWhom(null);
            replyComment.setCustomer(null);
            replyComment.setFood(null);
            if(replyComment.getParentComm()==null)
            {
                comment.setFood(replyFood);
                comment.setCustomer(customer);
                comment.setToWhom(null);
                comment.setParentComm(replyComment);
                comment.setChildrenComm(null);
                comment.setCommTime(time);
                comment.setCommText(text);
                comment.setCommLikes(0);
            }else if(replyComment.getParentComm()!=null)
            {//2.2 如果replyComment 是children
                comment.setFood(replyFood);
                comment.setCustomer(customer);
                comment.setToWhom(replyComment.getCustomer());
                comment.setParentComm(replyComment.getParentComm());
                comment.setChildrenComm(null);
                comment.setCommTime(time);
                comment.setCommText(text);
                comment.setCommLikes(0);
            }
        }
        commentDao.save(comment);
    }

    //查看Customer的Reply和Comment
    public List<Comment> ShowAll(Customer customer, int direction, boolean myComm)
    {
        Set<Comment> myComments = customer.getComments();
        Set<Comment> myReplys = customer.getReplys();
        for(Comment comment : (myComm? myComments : myReplys))
        {
            comment.setToWhom(null);
            comment.setCustomer(null);
            comment.setFood(null);
            comment.setChildrenComm(null);
            comment.setParentComm(null);
        }
        List<Comment> commentList = new ArrayList<Comment>(myComm? myComments : myReplys);
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return direction * o1.getCommTime().compareTo(o2.getCommTime());
            }
        });
        return commentList;
    }
    //查看该Food的所有Comment
    public List<Comment> ShowFoodComment(Food food, int direction, boolean paid)
    {
        Set<Comment> commentSet = foodDao.getOne(food.getFoodId()).getComments();
        for(Comment comment: commentSet)
        {
            if(comment.getParentComm()!=null)
            {//非直接对Food评论的Comment要从commentSet中移除，只能在下一个请求通过getChildrenComm()访问
                commentSet.remove(comment);
            }else{
                comment.setFood(null);
                comment.setCustomer(null);
                comment.setToWhom(null);
                comment.setChildrenComm(null);
            }
        }
        //所有-》food 的 评论List
        List<Comment> commentList = new ArrayList<Comment>(commentSet);
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return direction * o1.getCommTime().compareTo(o2.getCommTime());
            }
        });
        //热评list（点赞数前3）
        List<Comment> hotCommList = new ArrayList<Comment>(commentSet);
        Collections.sort(hotCommList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o1.getCommLikes()-o2.getCommLikes();
            }
        });
        //两表合并打包
        commentList.addAll(0,hotCommList.subList(0,2));
        return commentList;
    }
}
