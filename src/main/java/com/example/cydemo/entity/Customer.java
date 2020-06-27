package com.example.cydemo.entity;

import com.example.cydemo.dto.CustomerDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long customerId;
    @Column(name = "name")
    private String customerName;
    @Column(name = "sex")
    private String customerSex;
    @Column(name = "identity")
    private String identity;
    @Column(name = "university")
    private String university;
    @Column(name = "school")
    private String school;
    @Column(name = "hometown")
    private String hometown;
    @Column(name = "cust_intro")
    private String customerIntro;
    @Column(name = "picurl")
    private String custPic;

    //Customer <-- Mail外键
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Mail> mails = new HashSet<>();
    //Customer <-- Post外键
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();
    //Customer <-- Bill外键
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Bill> bills = new HashSet<>();
    //Customer <-- Comment外键   自己写的评论
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
    //Customer <-- ToWhom外键   收到的回复
    @OneToMany(mappedBy = "toWhom",cascade = CascadeType.ALL)
    private Set<Comment> replys = new HashSet<>();

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getCustomerIntro() {
        return customerIntro;
    }

    public void setCustomerIntro(String customerIntro) {
        this.customerIntro = customerIntro;
    }

    public Set<Mail> getMails() {
        return mails;
    }

    public void setMails(Set<Mail> mails) {
        this.mails = mails;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Comment> getReplys() {
        return replys;
    }

    public void setReplys(Set<Comment> replys) {
        this.replys = replys;
    }

    public String getCustPic() {
        return custPic;
    }

    public void setCustPic(String custPic) {
        this.custPic = custPic;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerSex='" + customerSex + '\'' +
                ", identity='" + identity + '\'' +
                ", university='" + university + '\'' +
                ", school='" + school + '\'' +
                ", hometown='" + hometown + '\'' +
                ", customerIntro='" + customerIntro + '\'' +
                ", custPic='" + custPic + '\'' +
                ", mails=" + mails +
                ", posts=" + posts +
                ", bills=" + bills +
                ", comments=" + comments +
                ", replys=" + replys +
                '}';
    }

    public CustomerDto toDto()
    {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(this.customerId);
        customerDto.setCustomerName(this.customerName);
        customerDto.setCustPic(this.custPic);
        return customerDto;
    }
    public CustomerDto toCustDto()//用于个人信息查询/修改的封装类
    {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(this.customerId);
        customerDto.setCustomerName(this.customerName);
        customerDto.setCustPic(this.custPic);
        customerDto.setCustomerSex(this.customerSex);
        customerDto.setIdentity(this.identity);
        customerDto.setUniversity(this.university);
        customerDto.setSchool(this.school);
        customerDto.setHometown(this.hometown);
        customerDto.setCustomerIntro(this.customerIntro);

        return customerDto;
    }
}
