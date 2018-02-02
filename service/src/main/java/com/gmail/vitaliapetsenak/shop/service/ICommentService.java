package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;

import java.util.List;

public interface ICommentService {
    List<CommentDTO> getByNews(Long newsId,Integer page,Integer count);

    CommentDTO getById(Long id);

    Long add(CommentDTO commentDTO);

    void delete(CommentDTO commentDTO);

    void deleteByNews(Long newsId);

    void delete(Long id);
}
