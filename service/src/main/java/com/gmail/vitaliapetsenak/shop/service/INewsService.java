package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;

import java.util.List;

public interface INewsService {

    List<NewsDTO> getAll(Integer page, Integer count);

    NewsDTO getById(Long newsId);

    NewsDTO getByIdWithComments(Long newsId);

    Long add(NewsDTO newsDTO);

    void update(NewsDTO newsDTO);

    void delete(NewsDTO newsDTO);

    void delete(Long id);

    Long getPageCount(Integer page, Integer count);
}
