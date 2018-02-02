package com.gmail.vitaliapetsenak.shop.service.model;


import com.gmail.vitaliapetsenak.shop.repository.model.News;

import java.util.List;

public class NewsDTO {

    private Long id;
    private UserDTO user;
    private String author;
    private String name;
    private String description;
    private String timestamp;
    private Long fileId;
    private List<CommentDTO> comments;

    public NewsDTO(News news) {
        this(newBuilder()
                .id(news.getId())
                .user(new UserDTO(news.getUser()))
                .author(news.getAuthor())
                .name(news.getName())
                .description(news.getDescription())
                .fileId(news.getId())
        );
    }

    private NewsDTO(Builder builder) {
        setId(builder.id);
        setUser(builder.user);
        setAuthor(builder.author);
        setName(builder.name);
        setDescription(builder.description);
        setTimestamp(builder.timestamp);
        setFileId(builder.fileId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public static final class Builder {
        private Long id;
        private UserDTO user;
        private String author;
        private String name;
        private String description;
        private String timestamp;
        private Long fileId;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder fileId(Long fileId) {
            this.fileId = fileId;
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

        public Builder timestamp(String timestamp) {
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
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", fileId=" + fileId +
                '}';
    }
}
