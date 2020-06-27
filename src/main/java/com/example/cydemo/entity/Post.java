package com.example.cydemo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id",columnDefinition = "bigint")
    private long postId;
    @Column(name = "text")
    private String postText;
    @Column(name = "picurl")
    private String postPic;
    @Column(name = "likes")
    private int postLikes;
    @Column(name = "time")
    private Timestamp postTime;

    //Customer <-- Post外键
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",referencedColumnName = "cust_id")
    private Customer customer;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostPic() {
        return postPic;
    }

    public void setPostPic(String postPic) {
        this.postPic = postPic;
    }

    public int getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(int postLikes) {
        this.postLikes = postLikes;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postText='" + postText + '\'' +
                ", postPic='" + postPic + '\'' +
                ", postLikes=" + postLikes +
                ", postTime=" + postTime +
                ", customer=" + customer +
                '}';
    }
}
