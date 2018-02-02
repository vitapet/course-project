package com.gmail.vitaliapetsenak.shop.service.converter;

import com.gmail.vitaliapetsenak.shop.repository.model.News;
import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsConverter {
    @Autowired
    private DateTimeFormatter dateTimeFormatter;
    @Autowired
    private UserConverter userConverter;

    public News convert(NewsDTO newsDTO) throws ParseException {
        User user = new User();
        if (newsDTO.getId() != null) {
            userConverter.convert(user, newsDTO.getUser());
        }
        News news = new News();
        if (newsDTO.getId() != null) {
            news.setId(newsDTO.getId());
        }
        if (newsDTO.getUser() != null) {
            news.setUser(user);
        }
        if (newsDTO.getAuthor() != null) {
            news.setAuthor(newsDTO.getAuthor());
        }
        if (newsDTO.getName() != null) {
            news.setName(newsDTO.getName());
        }
        if (newsDTO.getDescription() != null) {
            news.setDescription(newsDTO.getDescription());
        }
        if (newsDTO.getTimestamp() != null) {
            news.setTimestamp(dateTimeFormatter.parse(newsDTO.getTimestamp()));
        }
        return news;
    }

    public List<NewsDTO> newsToDTO(List<News> newsList) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        if (newsList != null) {
            for (News news : newsList) {
                NewsDTO newsDTO = new NewsDTO(news);
                newsDTO.setTimestamp(dateTimeFormatter.format(news.getTimestamp()));
                newsDTOS.add(newsDTO);
            }
            return newsDTOS;
        }
        return null;
    }
}
