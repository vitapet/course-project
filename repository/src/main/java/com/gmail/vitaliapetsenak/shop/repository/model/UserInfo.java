package com.gmail.vitaliapetsenak.shop.repository.model;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 5010215467423541790L;

    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user")
    )
    @Id
    @GeneratedValue(generator = "gen")
    @Column(name = "F_USER_ID")
    private Long userIid;

    @Column(name = "F_FIRST_NAME")
    private String firstName;

    @Column(name = "F_SURNAME")
    private String surname;

    @Column(name = "F_BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Column(name = "F_PHONE")
    private String phone;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "info")
    private UserAddress address;

    public UserInfo() {
    }

    public UserInfo(String firstName, String surname, Date birthDate, String phone) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public Long getUserIid() {
        return userIid;
    }

    public void setUserIid(Long userIid) {
        this.userIid = userIid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userIid=" + userIid +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                ", address=" + address +
                '}';
    }
}
