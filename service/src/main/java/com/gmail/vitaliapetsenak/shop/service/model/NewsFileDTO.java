package com.gmail.vitaliapetsenak.shop.service.model;

import com.gmail.vitaliapetsenak.shop.repository.model.NewsFile;

public class NewsFileDTO {
    private Long newsId;
    private String name;
    private String location;

    public NewsFileDTO() {
    }

    public NewsFileDTO(Long newsId, String name, String location) {
        this.newsId = newsId;
        this.name = name;
        this.location = location;
    }

    public NewsFileDTO(NewsFile newsFile) {
        setNewsId(newsFile.getNewsId());
        setName(newsFile.getName());
        setLocation(newsFile.getLocation());
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
