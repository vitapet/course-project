package com.gmail.vitaliapetsenak.shop.service.converter;

import com.gmail.vitaliapetsenak.shop.repository.model.Comment;
import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.service.INewsService;
import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentConverter {
    @Autowired
    private DateTimeFormatter dateTimeFormatter;
    @Autowired
    private UserConverter userConverter;

    public Comment convert(CommentDTO commentDTO) throws ParseException {
        User user = new User();
        userConverter.convert(user, commentDTO.getUser());
        Comment comment = new Comment();
        if (commentDTO.getId() != null) {
            comment.setId(commentDTO.getId());
        }
        if (commentDTO.getText() != null) {
            comment.setText(commentDTO.getText());
        }
        if (commentDTO.getUser() != null) {
            comment.setUser(user);
        }
        if (commentDTO.getTimestamp() != null) {
            comment.setTimestamp(dateTimeFormatter.parse(commentDTO.getTimestamp()));
        }
        return comment;
    }

    public List<CommentDTO> commentsToDTO(List<Comment> comments) {
        List<CommentDTO> commentsDTO = new ArrayList<>();
        if (comments != null) {
            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO(comment);
                commentDTO.setTimestamp(dateTimeFormatter.format(comment.getTimestamp()));
                commentsDTO.add(commentDTO);
            }
            return commentsDTO;
        }
        return null;
    }
}
