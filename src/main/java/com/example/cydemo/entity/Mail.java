package com.example.cydemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "mail")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id",columnDefinition = "bigint")
    private long mailId;
    @Column(name = "text",columnDefinition = "text")
    private String mailText;
    @Column(name = "picurl")
    private String mailPic;

    //Customer <-- Mail外键
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",referencedColumnName = "cust_id")
    private Customer customer;
    //Customer <-- Mail外键
    @ManyToOne(targetEntity = Restaurant.class)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "rest_id")
    private Restaurant restaurant;

    public long getMailId() {
        return mailId;
    }

    public void setMailId(long mailId) {
        this.mailId = mailId;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public String getMailPic() {
        return mailPic;
    }

    public void setMailPic(String mailPic) {
        this.mailPic = mailPic;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "mailId=" + mailId +
                ", mailText='" + mailText + '\'' +
                ", mailPic='" + mailPic + '\'' +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                '}';
    }
}
