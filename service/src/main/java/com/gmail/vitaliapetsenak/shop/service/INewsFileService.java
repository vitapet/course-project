package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.service.model.NewsFileDTO;

import java.io.File;

public interface INewsFileService {

    File getFileById(Long id);

    NewsFileDTO getDTOById(Long id);

    void update(NewsFileDTO fileDTO);

    void add(NewsFileDTO fileDTO);
}
