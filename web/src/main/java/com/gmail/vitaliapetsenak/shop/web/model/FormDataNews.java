package com.gmail.vitaliapetsenak.shop.web.model;

import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import org.springframework.web.multipart.MultipartFile;

public class FormDataNews {
    private Long id;
    private String author;
    private String name;
    private String description;
    private String timestamp;
    private MultipartFile file;

    public FormDataNews() {
    }

    public FormDataNews(NewsDTO newsDTO) {
        setId(newsDTO.getId());
        setAuthor(newsDTO.getAuthor());
        setName(newsDTO.getName());
        setDescription(newsDTO.getDescription());
        setTimestamp(newsDTO.getTimestamp());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
