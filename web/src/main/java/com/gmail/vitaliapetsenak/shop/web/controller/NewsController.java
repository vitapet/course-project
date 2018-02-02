package com.gmail.vitaliapetsenak.shop.web.controller;

import com.gmail.vitaliapetsenak.shop.service.INewsService;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.web.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private INewsService newsService;

    private static Pagination pagination = new Pagination();

    @GetMapping(value = "/news/selected/{id}")
    public String getNews(Model model, @PathVariable Long id) {
        NewsDTO news = newsService.getByIdWithComments(id);
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/news/page/{page}")
    public String showNewsPages(@PathVariable Long page, Model model) {
        pagination.setCurPage(page);
        setPagination();
        model.addAttribute("pagination", pagination);
        List<NewsDTO> newsList = newsService.getAll(pagination.getCurPage().intValue(), 2);
        model.addAttribute("newsList", newsList);
        return "start";
    }

    private void setPagination() {
        pagination.setUri("news/page");
        pagination.setCount(newsService.getPageCount(pagination.getCurPage().intValue(), 2));
    }
}
