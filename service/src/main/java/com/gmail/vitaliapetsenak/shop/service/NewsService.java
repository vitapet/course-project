package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.repository.dao.CommentsDAOIml;
import com.gmail.vitaliapetsenak.shop.repository.dao.NewsDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.CommentsInterface;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.NewsInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.service.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;

import java.util.ArrayList;
import java.util.List;

public class NewsService {
    private static volatile NewsService instance;
    private NewsInterface newsDAO = NewsDAOImpl.getInstance();
    private CommentsInterface commentsDAO = CommentsDAOIml.getInstance();

    private NewsService() {
    }

    public static synchronized NewsService getInstance() {
        if (instance == null) {
            instance = new NewsService();
        }
        return instance;
    }

    public List<NewsDTO> getAllNews() {
        List<NewsDTO> newsDTOList = new ArrayList<>();
        List<News> newsList = newsDAO.readAll();
        for (News news : newsList) {
            newsDTOList.add(new NewsDTO(news));
        }
        return newsDTOList;
    }

    public NewsDTO getNews(Long id) {
        News news = newsDAO.readById(id);
        if (news != null) {
            return new NewsDTO(news);
        }
        return null;
    }

    public void addNews(NewsDTO newsDTO) {
        News news = News.newBuilder().build();
        Converter.convert(newsDTO, news);
        newsDAO.create(news);
    }

    public void updateNews(NewsDTO newsDTO) {
        News news = newsDAO.readById(newsDTO.getId());
        Converter.convert(newsDTO, news);
        newsDAO.update(news);
    }

    public void deleteNews(Long id) {
        commentsDAO.deleteNewsComments(id);
        newsDAO.delete(id);
    }

    public NewsDTO readByName(String name) {
        News news = News.newBuilder()
                .name(name)
                .build();
        NewsDTO newsDTO = new NewsDTO(newsDAO.read(news));
        return newsDTO;
    }
}
