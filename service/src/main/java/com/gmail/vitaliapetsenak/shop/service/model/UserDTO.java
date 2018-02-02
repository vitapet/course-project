package com.gmail.vitaliapetsenak.shop.service.model;


import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;

import java.util.List;

public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private String firstName;
    private String surname;
    private String birthDate;
    private String phone;
    private String country;
    private String city;
    private String street;
    private String house;
    private String block;
    private String apartment;
    private UserStatus status;
    private String passwordCheck;

    private List<OrderDTO> orders;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(newBuilder()
                .id(user.getId())
                .login(user.getLogin())
                .firstName(user.getUserInfo().getFirstName())
                .surname(user.getUserInfo().getSurname())
                .status(UserStatus.valueOf(user.getStatus().toString()))

        );
        setPasswordCheck(user.getPassword());
    }

    private UserDTO(Builder builder) {
        setId(builder.id);
        setLogin(builder.login);
        setPassword(builder.password);
        setRole(builder.role);
        setFirstName(builder.firstName);
        setSurname(builder.surname);
        setBirthDate(builder.birthDate);
        setPhone(builder.phone);
        setCountry(builder.country);
        setCity(builder.city);
        setStreet(builder.street);
        setHouse(builder.house);
        setBlock(builder.block);
        setApartment(builder.apartment);
        setStatus(builder.status);
        setPasswordCheck(getPassword());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public static final class Builder {
        private long id;
        private String login;
        private String password;
        private UserRole role;
        private String firstName;
        private String surname;
        private String birthDate;
        private String phone;
        private String country;
        private String city;
        private String street;
        private String house;
        private String block;
        private String apartment;
        private UserStatus status;

        private Builder() {
            super();

        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder house(String house) {
            this.house = house;
            return this;
        }

        public Builder block(String block) {
            this.block = block;
            return this;
        }

        public Builder apartment(String apartment) {
            this.apartment = apartment;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", block=" + block +
                ", apartment=" + apartment +
                ", status=" + status +
                '}';
    }
}
