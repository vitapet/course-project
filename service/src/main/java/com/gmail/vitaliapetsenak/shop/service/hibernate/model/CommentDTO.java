package com.gmail.vitaliapetsenak.shop.service.hibernate.model;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Comment;

import java.sql.Timestamp;

public class CommentDTO {
    private Long id;
    private String text;
    private UserDTO user;
    private NewsDTO news;
    private Timestamp timestamp;

    public CommentDTO(Comment comment) {
        this(newBuilder()
                .id(comment.getId())
                .text(comment.getText())
                .user(new UserDTO(comment.getUser()))
                .news(new NewsDTO(comment.getNews()))
                .timestamp(comment.getTimestamp())
        );
    }

    private CommentDTO(Builder builder) {
        setId(builder.id);
        setText(builder.text);
        setUser(builder.user);
        setNews(builder.news);
        setTimestamp(builder.timestamp);
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public NewsDTO getNews() {
        return news;
    }

    public void setNews(NewsDTO news) {
        this.news = news;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public static final class Builder {
        private long id;
        private String text;
        private UserDTO user;
        private NewsDTO news;
        private Timestamp timestamp;

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

        public Builder user(UserDTO user) {
            this.user = user;
            return this;
        }

        public Builder news(NewsDTO news) {
            this.news = news;
            return this;
        }

        public Builder timestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public CommentDTO build() {
            return new CommentDTO(this);
        }
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
