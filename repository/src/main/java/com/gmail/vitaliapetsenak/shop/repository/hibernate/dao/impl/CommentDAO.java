package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.CommentInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Comment;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.News;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends AbstractDAO<Comment, Long> implements CommentInterface {

    private static volatile CommentDAO instance;
    private static NewsDAO newsDAO = NewsDAO.getInstance();

    private CommentDAO() {
    }

    public static synchronized CommentDAO getInstance() {
        if (instance == null) {
            instance = new CommentDAO();
        }
        return instance;
    }

    @Override
    public List<Comment> getNewsComments(Long newsId) {
        News news = getSession().get(News.class, newsId);
//        Hibernate.initialize(news.getComments());
        List<Comment> comments = new ArrayList<>(news.getComments());
        comments.sort((Comment first, Comment second) ->
                second.getTimestamp().compareTo(first.getTimestamp())
        );
        return comments;
    }

    @Override
    public void deleteNewsComments(Long newsId) {
        News news = getSession().get(News.class, newsId);
        news.getComments().clear();
    }
}
