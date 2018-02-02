package com.gmail.vitaliapetsenak.shop.service.impl;


import com.gmail.vitaliapetsenak.shop.repository.dao.ICommentDAO;
import com.gmail.vitaliapetsenak.shop.repository.dao.INewsDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.Comment;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.service.ICommentService;
import com.gmail.vitaliapetsenak.shop.service.converter.CommentConverter;
import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements ICommentService {

    private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private ICommentDAO commentDAO;

    @Autowired
    private INewsDAO newsDAO;

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    private CommentConverter commentConverter;


    @Override
    @Transactional
    public List<CommentDTO> getByNews(Long newsId, Integer page, Integer count) {
        List<Comment> comments = commentDAO.getNewsComments(newsId, page, count);
        return commentConverter.commentsToDTO(comments);
    }

    @Override
    @Transactional
    public CommentDTO getById(Long id) {
        return new CommentDTO(commentDAO.findById(id));
    }

    @Override
    @Transactional
    public Long add(CommentDTO commentDTO) {
        try {
            Comment comment = null;
            comment = commentConverter.convert(commentDTO);
            comment.setTimestamp(dateTimeFormatter.parse(commentDTO.getTimestamp()));
            comment.setNews(newsDAO.findById(commentDTO.getNewsId()));
            return commentDAO.create(comment);
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(CommentDTO commentDTO) {
        Comment comment = null;
        try {
            comment = commentConverter.convert(commentDTO);
            commentDAO.delete(comment);
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    @Transactional
    public void deleteByNews(Long newsId) {
        News news = newsDAO.findById(newsId);
        news.getComments().clear();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Comment comment = commentDAO.findById(id);
        commentDAO.delete(comment);
    }
}
