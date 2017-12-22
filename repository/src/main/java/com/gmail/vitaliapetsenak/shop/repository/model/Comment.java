package com.gmail.vitaliapetsenak.shop.repository.model;

import java.sql.Timestamp;

public class Comment {
    private Long id;
    private String text;
    private User user;
    private News news;
    private Timestamp date;

    private Comment(Builder builder) {
        setId(builder.id);
        setText(builder.text);
        setUser(builder.user);
        setNews(builder.news);
        setDate(builder.date);
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public static final class Builder {
        private long id;
        private String text;
        private User user;
        private News news;
        private Timestamp date;

        private Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder news(News news) {
            this.news = news;
            return this;
        }

        public Builder date(Timestamp date) {
            this.date = date;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", news=" + news +
                ", date=" + date +
                '}';
    }
}