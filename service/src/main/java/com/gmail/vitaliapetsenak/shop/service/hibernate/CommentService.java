package com.gmail.vitaliapetsenak.shop.service.hibernate;

import com.gmail.vitaliapetsenak.shop.service.hibernate.model.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getByNews(Long newsId);

    Long add(CommentDTO commentDTO);

    void delete(CommentDTO commentDTO);

    void deleteByNews(Long newsId);
}
