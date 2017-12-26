package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.NewsInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.News;

public class NewsDAO extends AbstractDAO<News, Long> implements NewsInterface {

    private static volatile NewsDAO instance;

    private NewsDAO() {
    }

    public static synchronized NewsDAO getInstance() {
        if (instance == null) {
            instance = new NewsDAO();
        }
        return instance;
    }
}
