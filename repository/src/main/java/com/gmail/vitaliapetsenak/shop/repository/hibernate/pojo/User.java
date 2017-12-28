package com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_USERS")
public class User implements Serializable {
    private static final long serialVersionUID = 6466433850636063538L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;

    @Column(name = "F_LOGIN")
    private String login;

    @Column(name = "F_PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_ROLE")
    private UserRole role;

    @Column(name = "F_FIRST_NAME")
    private String firstName;

    @Column(name = "F_SURNAME")
    private String surname;

    @Column(name = "F_BIRTH_DATE")
    private Date birthDate;

    @Column(name = "F_PHONE")
    private String phone;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "F_STATUS")
    private UserStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<News> news = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public User() {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public void addNews(News news) {
        this.news.add(news);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Set<Order> returnOrders() {
        return this.orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(birthDate, user.birthDate) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(country, user.country) &&
                Objects.equals(city, user.city) &&
                Objects.equals(street, user.street) &&
                Objects.equals(house, user.house) &&
                Objects.equals(block, user.block) &&
                Objects.equals(apartment, user.apartment) &&
                status == user.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, role, firstName, surname, birthDate, phone, country, city, street, house, block, apartment, status);
    }

    @Override
    public String toString() {
        return "User{" +
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
