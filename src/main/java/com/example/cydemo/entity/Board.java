package com.example.cydemo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private long boardId;
    @Column(name = "category")
    private String boardCat;
    @Column(name = "text")
    private String boardText;
    @Column(name = "picurl")
    private String boardPic;
    @Column(name = "time")
    private Timestamp boardTime;

    //restaurant <-- board外键
    @ManyToOne(targetEntity = Restaurant.class)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "rest_id")
    private Restaurant restaurant;

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public String getBoardCat() {
        return boardCat;
    }

    public void setBoardCat(String boardCat) {
        this.boardCat = boardCat;
    }

    public String getBoardText() {
        return boardText;
    }

    public void setBoardText(String boardText) {
        this.boardText = boardText;
    }

    public String getBoardPic() {
        return boardPic;
    }

    public void setBoardPic(String boardPic) {
        this.boardPic = boardPic;
    }

    public Timestamp getBoardTime() {
        return boardTime;
    }

    public void setBoardTime(Timestamp boardTime) {
        this.boardTime = boardTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", boardCat='" + boardCat + '\'' +
                ", boardText='" + boardText + '\'' +
                ", boardPic='" + boardPic + '\'' +
                ", boardTime=" + boardTime +
                ", restaurant=" + restaurant +
                '}';
    }
}
