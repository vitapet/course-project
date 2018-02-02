package com.gmail.vitaliapetsenak.shop.service.converter;

import com.gmail.vitaliapetsenak.shop.repository.model.NewsFile;
import com.gmail.vitaliapetsenak.shop.service.model.NewsFileDTO;
import org.springframework.stereotype.Component;

@Component
public class NewsFileConverter {

    public NewsFile convert(NewsFileDTO fileDTO) {
        NewsFile newsFile = new NewsFile();
        if (fileDTO.getNewsId() != null) {
            newsFile.setNewsId(fileDTO.getNewsId());
        }
        if (fileDTO.getName() != null) {
            newsFile.setName(fileDTO.getName());
        }
        if (fileDTO.getLocation() != null) {
            newsFile.setLocation(fileDTO.getLocation());
        }
        return newsFile;
    }
}
