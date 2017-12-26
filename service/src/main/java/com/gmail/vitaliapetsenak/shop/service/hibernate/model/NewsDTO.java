package com.gmail.vitaliapetsenak.shop.service.hibernate.model;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Comment;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.News;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class NewsDTO {
    private Long id;
    private UserDTO user;
    private String author;
    private String name;
    private String description;
    private Timestamp timestamp;
    private String image;
    private Set<CommentDTO> comments;

    public NewsDTO(News news) {
        this(newBuilder()
                .id(news.getId())
                .user(new UserDTO(news.getUser()))
                .author(news.getAuthor())
                .name(news.getName())
                .description(news.getDescription())
                .timestamp(news.getTimestamp())
                .image(news.getImage())
        );
    }

    private NewsDTO(Builder builder) {
        setId(builder.id);
        setUser(builder.user);
        setAuthor(builder.author);
        setName(builder.name);
        setDescription(builder.description);
        setTimestamp(builder.timestamp);
        setImage(builder.image);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public void setCommentsFromPojo(Set<Comment> comments) {
        Set<CommentDTO> dtos = new HashSet<>();
        for (Comment comment : comments) {
            dtos.add(new CommentDTO(comment));
        }
        this.comments = dtos;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public static final class Builder {
        private long id;
        private UserDTO user;
        private String author;
        private String name;
        private String description;
        private Timestamp timestamp;
        private String image;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder user(UserDTO user) {
            this.user = user;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder timestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public NewsDTO build() {
            return new NewsDTO(this);
        }
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", image='" + image + '\'' +
                '}';
    }
}
