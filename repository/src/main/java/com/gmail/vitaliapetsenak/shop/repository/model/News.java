package com.gmail.vitaliapetsenak.shop.repository.model;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "T_NEWS")
public class News implements Serializable {

    private static final long serialVersionUID = 1875419390535095184L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "F_USER_ID")
    private User user;

    @Column(name = "F_AUTHOR")
    private String author;

    @Column(name = "F_NAME")
    private String name;

    @Column(name = "F_DESCRIPTION")
    @Type(type = "text")
    private String description;

    @Column(name = "F_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "news")
    private NewsFile newsFile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public News() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public NewsFile getNewsFile() {
        return newsFile;
    }

    public void setNewsFile(NewsFile newsFile) {
        this.newsFile = newsFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) &&
                Objects.equals(user, news.user) &&
                Objects.equals(author, news.author) &&
                Objects.equals(name, news.name) &&
                Objects.equals(description, news.description) &&
                Objects.equals(timestamp, news.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, author, name, description, timestamp);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", user=" + user +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
