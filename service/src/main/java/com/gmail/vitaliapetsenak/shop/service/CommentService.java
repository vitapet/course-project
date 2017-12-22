package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.repository.dao.CommentsDAOIml;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.CommentsInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.Comment;
import com.gmail.vitaliapetsenak.shop.service.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    private static volatile CommentService instance;
    private CommentsInterface commentDAO = CommentsDAOIml.getInstance();

    private CommentService() {
    }

    public static synchronized CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }

    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentDAO.readAll();
        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment comment : comments) {
            commentsDTO.add(new CommentDTO(comment));
        }
        return commentsDTO;
    }

    public CommentDTO getComment(Long id) {
        Comment comment = commentDAO.readById(id);
        if (comment != null) {
            return new CommentDTO(comment);
        }
        return null;
    }

    public void addComment(CommentDTO commentDTO) {
        Comment comment = Comment.newBuilder().build();
        Converter.convert(commentDTO, comment);
        commentDAO.create(comment);
    }

    public void deleteComment(Long id) {
        commentDAO.delete(id);
    }

    public List<CommentDTO> getByNews(Long newsId) {
        List<Comment> comments = commentDAO.getNewsComments(newsId);
        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment comment : comments) {
            commentsDTO.add(new CommentDTO(comment));
        }
        return commentsDTO;
    }

    public void deleteNewsComments(Long newsId) {
        commentDAO.deleteNewsComments(newsId);
    }
}
