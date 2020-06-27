package com.example.cydemo.entity;

import com.example.cydemo.dto.RestaurantDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_id")
    private long restId;
    @Column(name = "name")
    private String restName;
    @Column(name = "building")
    private String building;
    @Column(name = "floor")
    private int floor;
    @Column(name = "intro")
    private String restIntro;

    //restaurant <-- mail外键
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Mail> mails = new HashSet<>();
    //restaurant <-- food外键
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Food> foods = new HashSet<>();

    //restaurant <-- board外键
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private Set<Board> boards = new HashSet<>();

    public long getRestId() {
        return restId;
    }

    public void setRestId(long restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRestIntro() {
        return restIntro;
    }

    public void setRestIntro(String restIntro) {
        this.restIntro = restIntro;
    }

    public Set<Mail> getMails() {
        return mails;
    }

    public void setMails(Set<Mail> mails) {
        this.mails = mails;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<Board> getBoards() {
        return boards;
    }

    public void setBoards(Set<Board> boards) {
        this.boards = boards;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restId=" + restId +
                ", restName='" + restName + '\'' +
                ", building='" + building + '\'' +
                ", floor=" + floor +
                ", restIntro='" + restIntro + '\'' +
                ", mails=" + mails +
                ", foods=" + foods +
                ", boards=" + boards +
                '}';
    }
    public Restaurant(){}
    public Restaurant(long id, String restName, String building, int floor, String restIntro)
    {
        this.restId = id;
        this.restName = restName;
        this.building = building;
        this.floor = floor;
        this.restIntro = restIntro;
    }
    public RestaurantDto toDto()
    {
        RestaurantDto restaurantDto = new RestaurantDto(this.restId,this.restName,this.building,this.floor,this.restIntro);
        return restaurantDto;
    }
}
