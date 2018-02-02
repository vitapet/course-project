package com.gmail.vitaliapetsenak.shop.repository.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.INewsDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsDAO")
public class NewsDAOImpl extends GenericDAOImpl<News, Long> implements INewsDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<News> getNews(Integer page, Integer count) {
        int firstResult = 0;
        if (page != null) {
            firstResult = (page - 1) * count;
        }
        return getSession().createCriteria(News.class)
                .setFirstResult(firstResult)
                .setMaxResults(count != null ? count : 5)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long getPageCount(Integer page, Integer count) {
        Long rowsNum = (Long) getSession().createCriteria(News.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
        if (rowsNum % count == 0) {
            return rowsNum / count;
        } else {
            return rowsNum / count + 1;
        }
    }
}
