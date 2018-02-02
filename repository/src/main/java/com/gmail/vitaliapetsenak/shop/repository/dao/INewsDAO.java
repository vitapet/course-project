package com.gmail.vitaliapetsenak.shop.repository.dao;


import com.gmail.vitaliapetsenak.shop.repository.model.News;

import java.util.List;

public interface INewsDAO extends IGenericDAO<News, Long> {
    List<News> getNews(Integer page, Integer count);
    Long getPageCount(Integer page, Integer count);
}
