package com.gmail.vitaliapetsenak.shop.service.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.INewsDAO;
import com.gmail.vitaliapetsenak.shop.repository.dao.INewsFileDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.repository.model.NewsFile;
import com.gmail.vitaliapetsenak.shop.service.INewsFileService;
import com.gmail.vitaliapetsenak.shop.service.converter.NewsFileConverter;
import com.gmail.vitaliapetsenak.shop.service.model.NewsFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service("fileService")
public class NewsFileServiceImpl implements INewsFileService {

    @Autowired
    private INewsFileDAO newsFileDAO;
    @Autowired
    private INewsDAO newsDAO;

    @Autowired
    private NewsFileConverter newsFileConverter;

    @Transactional
    @Override
    public File getFileById(Long id) {
        NewsFile newsFile = newsFileDAO.findById(id);
        return new File(newsFile.getLocation() + File.separator + newsFile.getName());
    }

    @Override
    @Transactional
    public NewsFileDTO getDTOById(Long id) {
        NewsFile newsFile = newsFileDAO.findById(id);
        return new NewsFileDTO(newsFile);
    }

    @Override
    @Transactional
    public void update(NewsFileDTO fileDTO) {
        NewsFile file = newsFileConverter.convert(fileDTO);
        newsFileDAO.update(file);
    }

    @Override
    @Transactional
    public void add(NewsFileDTO fileDTO) {
        NewsFile file = newsFileConverter.convert(fileDTO);
        News news = newsDAO.findById(fileDTO.getNewsId());
        file.setNews(news);
        newsFileDAO.create(file);
    }
}
