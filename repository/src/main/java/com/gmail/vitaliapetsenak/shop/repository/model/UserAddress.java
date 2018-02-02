package com.gmail.vitaliapetsenak.shop.repository.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_user_address")
public class UserAddress implements Serializable {

    private static final long serialVersionUID = -158778496885351938L;

    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "info")
    )
    @Id
    @GeneratedValue(generator = "gen")
    @Column(name = "F_USER_ID", nullable = false)
    private Long userId;
    @Column(name = "F_COUNTRY")
    private String country;

    @Column(name = "F_CITY")
    private String city;

    @Column(name = "F_STREET")
    private String street;

    @Column(name = "F_HOUSE")
    private String house;

    @Column(name = "F_BLOCK")
    private Integer block;

    @Column(name = "F_APARTMENT")
    private Integer apartment;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserInfo info;

    public UserAddress() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public UserAddress(String country, String city, String street, String house, Integer block, Integer apartment) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.block = block;
        this.apartment = apartment;
    }

    public UserInfo getInfo() {
        return info;
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "userId=" + userId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", block=" + block +
                ", apartment=" + apartment +
                '}';
    }
}
