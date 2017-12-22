package com.gmail.vitaliapetsenak.shop.repository.model;

import java.sql.Timestamp;

public class News {
    private Long id;
    private User user;
    private String author;
    private String name;
    private String description;
    private Timestamp timestamp;
    private String image;

    private News(Builder builder) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public User getUser() {
        return user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final class Builder {
        private long id;
        private User user;
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

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
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

        public News build() {
            return new News(this);
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
