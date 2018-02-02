package com.gmail.vitaliapetsenak.shop.web.util;

import com.gmail.vitaliapetsenak.shop.service.ICommentService;
import com.gmail.vitaliapetsenak.shop.service.INewsFileService;
import com.gmail.vitaliapetsenak.shop.service.INewsService;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.service.model.NewsFileDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import com.gmail.vitaliapetsenak.shop.web.model.FormDataNews;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class NewsUtil {

    private static final Logger logger = Logger.getLogger(NewsUtil.class);
    @Autowired
    private INewsService newsService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private DateTimeFormatter dateTimeFormatter;
    @Autowired
    private INewsFileService newsFileService;
    @Autowired
    private Environment environment;

    public NewsUtil() {
    }

    public void saveNews(FormDataNews dataNews) {
        NewsDTO newsDTO = NewsDTO.newBuilder()
                .name(dataNews.getName())
                .description(dataNews.getDescription())
                .author(dataNews.getAuthor())
                .timestamp(dateTimeFormatter.format(Timestamp.valueOf(LocalDateTime.now())))
                .build();
        UserDTO user = new UserDTO();
        user.setId(getPrincipal().getUserId());
        newsDTO.setUser(user);
        Long newsId = newsService.add(newsDTO);
        String fileName = newsId + ".jpg";
        String path = environment.getProperty("upload.location") + File.separator + newsId + ".jpg";
        try {
            dataNews.getFile().transferTo(new File(path));
            NewsFileDTO newsFile = new NewsFileDTO();
            newsFile.setNewsId(newsId);
            newsFile.setName(fileName);
            newsFile.setLocation(environment.getProperty("upload.location"));
            newsFileService.add(newsFile);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void updateNews(FormDataNews dataNews) {
        NewsDTO newsDTO = NewsDTO.newBuilder()
                .id(dataNews.getId())
                .name(dataNews.getName())
                .description(dataNews.getDescription())
                .author(dataNews.getAuthor())
                .timestamp(dateTimeFormatter.format(Timestamp.valueOf(LocalDateTime.now())))
                .build();
        String path = environment.getProperty("upload.location") + File.separator + dataNews.getId() + ".jpg";
        if (!dataNews.getFile().isEmpty()) {
            File file = new File(path);
            file.delete();
            try {
                dataNews.getFile().transferTo(file);
                newsService.update(newsDTO);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public FormDataNews getForm(Long newsId) {
        NewsDTO newsDTO = newsService.getById(newsId);
        FormDataNews dataNews = new FormDataNews(newsDTO);
        return dataNews;
    }

    private UserPrincipal getPrincipal() {
        UserPrincipal user = null;
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null) {
            user = (UserPrincipal) principal;
        }
        return user;
    }
}
