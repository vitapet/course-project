package com.gmail.vitaliapetsenak.shop.repository.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.ICommentDAO;
import com.gmail.vitaliapetsenak.shop.repository.dao.INewsDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.Comment;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentDAO")
public class CommentDAOImpl extends GenericDAOImpl<Comment, Long> implements ICommentDAO {

    @Autowired
    private INewsDAO newsDAO;

    @Override
    public List<Comment> getNewsComments(Long newsId) {
        News news = newsDAO.findById(newsId);
        List<Comment> comments = news.getComments();
        comments.sort((Comment first, Comment second) ->
                second.getTimestamp().compareTo(first.getTimestamp())
        );
        return comments;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> getNewsComments(Long newsId, Integer page, Integer count) {
        return getSession().createCriteria(Comment.class)
                .add(Restrictions.eq("news.id",newsId))
                .setFirstResult(page!=null?page:0)
                .setMaxResults(count!=null?count:5)
                .list();
    }

    @Override
    public void deleteNewsComments(Long newsId) {
        News news = getSession().get(News.class, newsId);
        news.getComments().clear();
    }
}
