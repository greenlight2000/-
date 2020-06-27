package com.example.cydemo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commId;
    @Column(name = "likes")
    private int commLikes;
    @Column(name = "time")
    private Timestamp commTime;
    @Column(name = "text")
    private String commText;

    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name = "parent_id",referencedColumnName = "comment_id")
    private Comment parentComm;
    @OneToMany(mappedBy = "parentComm")
    private Set<Comment> childrenComm = new HashSet<>();

    //food <-- comment外键
    @ManyToOne(targetEntity = Food.class)
    @JoinColumn(name = "food_id",referencedColumnName = "food_id")
    private Food food;
    //customer <-- comment外键
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",referencedColumnName = "cust_id")
    private Customer customer;
    //customer <-- comment外键（回复对象）
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "to_whom",referencedColumnName = "cust_id")
    private Customer toWhom;

    public long getCommId() {
        return commId;
    }

    public void setCommId(long commId) {
        this.commId = commId;
    }

    public Comment getParentComm() {
        return parentComm;
    }

    public void setParentComm(Comment parentComm) {
        this.parentComm = parentComm;
    }

    public Set<Comment> getChildrenComm() {
        return childrenComm;
    }

    public void setChildrenComm(Set<Comment> childrenComm) {
        this.childrenComm = childrenComm;
    }

    public int getCommLikes() {
        return commLikes;
    }

    public void setCommLikes(int commLikes) {
        this.commLikes = commLikes;
    }

    public Timestamp getCommTime() {
        return commTime;
    }

    public void setCommTime(Timestamp commTime) {
        this.commTime = commTime;
    }

    public String getCommText() {
        return commText;
    }

    public void setCommText(String commText) {
        this.commText = commText;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getToWhom() {
        return toWhom;
    }

    public void setToWhom(Customer toWhom) {
        this.toWhom = toWhom;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commId=" + commId +
                ", commLikes=" + commLikes +
                ", commTime=" + commTime +
                ", commText='" + commText + '\'' +
                ", food=" + food +
                ", customer=" + customer +
                ", toWhom=" + toWhom +
                '}';
    }
}
