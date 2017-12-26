package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Comment;

import java.util.List;

public interface CommentInterface extends GenericDAO<Comment, Long> {
    List<Comment> getNewsComments(Long newsId);

    void deleteNewsComments(Long newsId);
}
