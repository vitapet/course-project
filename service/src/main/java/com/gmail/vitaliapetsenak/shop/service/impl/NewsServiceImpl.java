package com.gmail.vitaliapetsenak.shop.service.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.INewsDAO;
import com.gmail.vitaliapetsenak.shop.repository.dao.IUserDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.service.INewsService;
import com.gmail.vitaliapetsenak.shop.service.converter.CommentConverter;
import com.gmail.vitaliapetsenak.shop.service.converter.NewsConverter;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements INewsService {

    private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);

    @Autowired
    private DateTimeFormatter dateTimeFormatter;
    @Autowired
    private INewsDAO newsDAO;
    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private NewsConverter newsConverter;
    @Autowired
    private CommentConverter commentConverter;

    public NewsServiceImpl() {
    }

    @Override
    @Transactional
    public List<NewsDTO> getAll(Integer page, Integer count) {
        List<News> news = newsDAO.getNews(page, count);
        return newsConverter.newsToDTO(news);
    }

    @Override
    @Transactional
    public NewsDTO getById(Long newsId) {
        News news = newsDAO.findById(newsId);
        NewsDTO newsDTO = new NewsDTO(news);
        newsDTO.setTimestamp(dateTimeFormatter.format(news.getTimestamp()));
        return newsDTO;
    }

    @Override
    @Transactional
    public NewsDTO getByIdWithComments(Long newsId) {
        News news = newsDAO.findById(newsId);
        NewsDTO newsDTO = new NewsDTO(news);
        newsDTO.setTimestamp(dateTimeFormatter.format(news.getTimestamp()));
        newsDTO.setComments(commentConverter.commentsToDTO(news.getComments()));
        return newsDTO;
    }

    @Override
    @Transactional
    public Long add(NewsDTO newsDTO) {
        News news = null;
        try {
            news = newsConverter.convert(newsDTO);
            User user = userDAO.findById(newsDTO.getUser().getId());
            news.setUser(user);
            return newsDAO.create(news);
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    @Transactional
    public void update(NewsDTO newsDTO) {
        News news = newsDAO.findById(newsDTO.getId());
        news.setAuthor(newsDTO.getAuthor());
        news.setName(newsDTO.getName());
        news.setDescription(newsDTO.getDescription());
        newsDAO.update(news);
    }

    @Override
    @Transactional
    public void delete(NewsDTO newsDTO) {
        News news = null;
        try {
            news = newsConverter.convert(newsDTO);
            newsDAO.delete(news);
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        News news = newsDAO.findById(id);
        newsDAO.delete(news);
    }

    @Transactional
    @Override
    public Long getPageCount(Integer page, Integer count) {
        return newsDAO.getPageCount(page, count);
    }
}
