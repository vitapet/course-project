package com.gmail.vitaliapetsenak.shop.repository.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.INewsFileDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.NewsFile;
import org.springframework.stereotype.Repository;

@Repository("newsFileDAO")
public class NewsFileDAOImpl extends GenericDAOImpl<NewsFile, Long> implements INewsFileDAO {
}
