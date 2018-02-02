package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.UserInfo;

import java.util.Date;

public class UserInfoDTO {

    private Long userIid;
    private String firstName;
    private String surname;
    private Date birthDate;
    private String phone;

    public UserInfoDTO() {
    }

    public UserInfoDTO(UserInfo userInfo) {
        setUserIid(userInfo.getUserIid());
        setFirstName(userInfo.getFirstName());
        setSurname(userInfo.getSurname());
        setBirthDate(userInfo.getBirthDate());
        setPhone(userInfo.getPhone());
    }

    public UserInfoDTO(String firstName, String surname, Date birthDate, String phone) {
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

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userIid=" + userIid +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                '}';
    }
}
