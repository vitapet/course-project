package com.gmail.vitaliapetsenak.shop.repository.dao;


import com.gmail.vitaliapetsenak.shop.repository.model.Comment;

import java.util.List;

public interface ICommentDAO extends IGenericDAO<Comment, Long> {
    List<Comment> getNewsComments(Long newsId);
    List<Comment> getNewsComments(Long newsId,Integer page,Integer count);

    void deleteNewsComments(Long newsId);
}
