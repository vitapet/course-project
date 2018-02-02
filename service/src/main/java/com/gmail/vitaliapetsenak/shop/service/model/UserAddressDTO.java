package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.UserAddress;

public class UserAddressDTO {
    private Long userId;
    private String country;
    private String city;
    private String street;
    private String house;
    private Integer block;
    private Integer apartment;

    public UserAddressDTO() {
    }

    public UserAddressDTO(UserAddress userAddress) {
        setUserId(userAddress.getUserId());
        setCountry(userAddress.getCountry());
        setCity(userAddress.getCity());
        setStreet(userAddress.getStreet());
        setHouse(userAddress.getHouse());
        setBlock(userAddress.getBlock());
        setApartment(userAddress.getApartment());
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

    @Override
    public String toString() {
        return "UserAddressDTO{" +
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
