package com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_NEWS")
public class News implements Serializable {

    private static final long serialVersionUID = 1875419390535095184L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "F_USER_ID")
    private User user;

    @Column(name = "F_AUTHOR")
    private String author;

    @Column(name = "F_NAME")
    private String name;

    @Column(name = "F_DESCRIPTION")
    private String description;

    @Column(name = "F_TIMESTAMP")
    private Timestamp timestamp;

    @Column(name = "F_IMAGE")
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                Objects.equals(timestamp, news.timestamp) &&
                Objects.equals(image, news.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, author, name, description, timestamp, image);
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
                ", image='" + image + '\'' +
                '}';
    }
}
