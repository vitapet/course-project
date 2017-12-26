package com.gmail.vitaliapetsenak.shop.service.hibernate;

import com.gmail.vitaliapetsenak.shop.service.hibernate.model.NewsDTO;

import java.util.List;

public interface NewsService {

    List<NewsDTO> getAll();

    NewsDTO getById(Long newsId);

    NewsDTO getByIdWithComments(Long newsId);

    Long add(NewsDTO newsDTO);

    void update(NewsDTO newsDTO);

    void delete(NewsDTO newsDTO);
}
