package com.gmail.vitaliapetsenak.shop.service.hibernate.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.NewsDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.NewsInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.News;
import com.gmail.vitaliapetsenak.shop.service.hibernate.NewsService;
import com.gmail.vitaliapetsenak.shop.service.hibernate.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.NewsDTO;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private static volatile NewsServiceImpl instance;
    private NewsInterface newsDAO = NewsDAO.getInstance();

    private NewsServiceImpl() {
    }

    public static synchronized NewsServiceImpl getInstance() {
        if (instance == null) {
            instance = new NewsServiceImpl();
        }
        return instance;
    }

    @Override
    public List<NewsDTO> getAll() {
        newsDAO.getSession().beginTransaction();
        List<News> newsList = newsDAO.findAll();
        newsDAO.getSession().getTransaction().commit();
        List<NewsDTO> news = new ArrayList<>();
        for (News item : newsList) {
            news.add(new NewsDTO(item));
        }
        newsDAO.getSession().getTransaction().commit();
        return news;
    }

    @Override
    public NewsDTO getById(Long newsId) {
        newsDAO.getSession().beginTransaction();
        News news = newsDAO.findById(newsId);
        newsDAO.getSession().getTransaction().commit();
        return new NewsDTO(news);
    }

    @Override
    public NewsDTO getByIdWithComments(Long newsId) {
        newsDAO.getSession().beginTransaction();
        News news = newsDAO.findById(newsId);
        Hibernate.initialize(news.getComments());
        NewsDTO newsDTO = new NewsDTO(news);
        newsDTO.setCommentsFromPojo(news.getComments());
        newsDAO.getSession().getTransaction().commit();
        return newsDTO;
    }

    @Override
    public Long add(NewsDTO newsDTO) {
        Long id;
        newsDAO.getSession().beginTransaction();
        News news = new News();
        Converter.convert(newsDTO, news);
        id = newsDAO.create(news);
        newsDAO.getSession().getTransaction().commit();
        return id;
    }

    @Override
    public void update(NewsDTO newsDTO) {
        newsDAO.getSession().beginTransaction();
        News news = new News();
        news.setId(newsDTO.getId());
        Converter.convert(newsDTO, news);
        newsDAO.update(news);
        newsDAO.getSession().getTransaction().commit();
    }

    @Override
    public void delete(NewsDTO newsDTO) {
        newsDAO.getSession().beginTransaction();
        News news = new News();
        news.setId(newsDTO.getId());
        Converter.convert(newsDTO, news);
        newsDAO.delete(news);
        newsDAO.getSession().getTransaction().commit();
    }
}
