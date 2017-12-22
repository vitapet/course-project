package com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "T_COMMENTS")
public class Comment implements Serializable {

    private static final long serialVersionUID = 7670502767925202704L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;

    @Column(name = "F_TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "F_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "F_NEWS_ID")
    private News news;

    @Column(name = "F_TIMESTAMP")
    private Timestamp timestamp;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(news, comment.news) &&
                Objects.equals(timestamp, comment.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, user, news, timestamp);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", news=" + news +
                ", timestamp=" + timestamp +
                '}';
    }
}
