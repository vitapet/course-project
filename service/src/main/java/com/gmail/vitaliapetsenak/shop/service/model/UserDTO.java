package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.User;

import java.sql.Date;

public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private RoleDTO role;
    private String firstName;
    private String surname;
    private Date birthDate;
    private String phone;
    private String country;
    private String city;
    private String street;
    private String house;
    private Integer block;
    private Integer apartment;
    private UserStatusDTO status;

    public UserDTO(User user) {
        this(newBuilder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(RoleDTO.valueOf(user.getRole().toString()))
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .country(user.getCountry())
                .city(user.getCity())
                .street(user.getStreet())
                .house(user.getHouse())
                .block(user.getBlock())
                .apartment(user.getApartment())
                .status(UserStatusDTO.valueOf(user.getStatus().toString()))
        );
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
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public UserStatusDTO getStatus() {
        return status;
    }

    public void setStatus(UserStatusDTO status) {
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

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
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

    public static final class Builder {
        private long id;
        private String login;
        private String password;
        private RoleDTO role;
        private String firstName;
        private String surname;
        private Date birthDate;
        private String phone;
        private String country;
        private String city;
        private String street;
        private String house;
        private int block;
        private int apartment;
        private UserStatusDTO status;

        private Builder() {

        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder status(UserStatusDTO status) {
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

        public Builder role(RoleDTO role) {
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

        public Builder birthDate(Date birthDate) {
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

        public Builder block(int block) {
            this.block = block;
            return this;
        }

        public Builder apartment(int apartment) {
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
                '}';
    }
}
