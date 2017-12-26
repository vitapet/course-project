package com.gmail.vitaliapetsenak.shop.service.hibernate.impl;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.CommentDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.NewsDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.CommentInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.NewsInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Comment;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.News;
import com.gmail.vitaliapetsenak.shop.service.hibernate.CommentService;
import com.gmail.vitaliapetsenak.shop.service.hibernate.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.CommentDTO;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private static volatile CommentServiceImpl instance;
    private static final CommentInterface commentDAO = CommentDAO.getInstance();
    private static final NewsInterface newsDAO = NewsDAO.getInstance();

    private CommentServiceImpl() {
    }

    public static synchronized CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    @Override
    public List<CommentDTO> getByNews(Long newsId) {
        commentDAO.getSession().beginTransaction();
        News news = newsDAO.findById(newsId);
        Hibernate.initialize(news.getComments());
        List<Comment> comments = new ArrayList<>(news.getComments());
        commentDAO.getSession().getTransaction().commit();
        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment comment : comments) {
            commentsDTO.add(new CommentDTO(comment));
        }
        return commentsDTO;
    }

    @Override
    public Long add(CommentDTO commentDTO) {
        Long id;
        commentDAO.getSession().beginTransaction();
        Comment comment = new Comment();
        Converter.convert(commentDTO, comment);
        id = commentDAO.create(comment);
        commentDAO.getSession().getTransaction().commit();
        return id;
    }

    @Override
    public void delete(CommentDTO commentDTO) {
        commentDAO.getSession().beginTransaction();
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        Converter.convert(commentDTO, comment);
        commentDAO.delete(comment);
        commentDAO.getSession().getTransaction().commit();
    }

    @Override
    public void deleteByNews(Long newsId) {
        commentDAO.getSession().beginTransaction();
        News news = newsDAO.findById(newsId);
        news.getComments().clear();
        commentDAO.getSession().getTransaction().commit();
    }
}
