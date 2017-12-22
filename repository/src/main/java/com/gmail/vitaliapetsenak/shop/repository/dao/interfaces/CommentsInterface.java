package com.gmail.vitaliapetsenak.shop.repository.dao.interfaces;

import com.gmail.vitaliapetsenak.shop.repository.model.Comment;

import java.util.List;

public interface CommentsInterface extends GenericDAO<Comment> {
    List<Comment> getNewsComments(Long newsId);

    void deleteNewsComments(Long newsId);
}
