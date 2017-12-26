package com.gmail.vitaliapetsenak.shop.service;


import com.gmail.vitaliapetsenak.shop.service.hibernate.NewsService;
import com.gmail.vitaliapetsenak.shop.service.hibernate.impl.NewsServiceImpl;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.NewsDTO;

public class App {
    public static void main(String[] args) {
        NewsService newsService = NewsServiceImpl.getInstance();
        NewsDTO newsDTO = newsService.getById(9L);
        System.out.println("by id" + newsDTO.getComments());
        newsDTO = newsService.getByIdWithComments(9L);
        System.out.println("by id with comments" + newsDTO.getComments());
    }
}
