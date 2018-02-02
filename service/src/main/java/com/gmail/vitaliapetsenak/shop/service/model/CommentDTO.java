package com.gmail.vitaliapetsenak.shop.service.model;


import com.gmail.vitaliapetsenak.shop.repository.model.Comment;

import javax.validation.constraints.Pattern;

public class CommentDTO {
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9.\\s]{1,250}$",message = "Invalid comment\'s length.")
    private String text;
    private UserDTO user;
    private Long newsId;
    private String timestamp;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        this(newBuilder()
                .id(comment.getId())
                .text(comment.getText())
                .user(new UserDTO(comment.getUser()))
                .newsId(comment.getNews().getId())
        );
    }

    private CommentDTO(Builder builder) {
        setId(builder.id);
        setText(builder.text);
        setUser(builder.user);
        setNewsId(builder.newsId);
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

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static final class Builder {
        private long id;
        private String text;
        private UserDTO user;
        private Long newsId;
        private String timestamp;

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

        public Builder newsId(Long newsId) {
            this.newsId = newsId;
            return this;
        }

        public Builder timestamp(String timestamp) {
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
                ", newsId=" + newsId +
                ", timestamp=" + timestamp +
                '}';
    }
}
