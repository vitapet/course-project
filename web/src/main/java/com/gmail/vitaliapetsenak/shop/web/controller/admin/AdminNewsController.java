package com.gmail.vitaliapetsenak.shop.web.controller.admin;

import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.service.ICommentService;
import com.gmail.vitaliapetsenak.shop.service.INewsService;
import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.web.model.FormDataNews;
import com.gmail.vitaliapetsenak.shop.web.model.LoggedInUser;
import com.gmail.vitaliapetsenak.shop.web.util.NewsUtil;
import com.gmail.vitaliapetsenak.shop.web.validator.NewsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminNewsController {

    @Autowired
    private INewsService newsService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private NewsUtil newsUtil;
    @Autowired
    private LoggedInUser loggedInUser;

    @Autowired
    private NewsValidator newsValidator;

    @InitBinder("news")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(newsValidator);
    }

    @GetMapping({"/admin/news", "/root/news"})
    public String showNews(Model model) {
        List<NewsDTO> newsList = newsService.getAll(null,null);
        model.addAttribute("newsList", newsList);
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_news";
        } else {
            return "root/root_news";
        }
    }

    @GetMapping({"/admin/news/add", "/root/news/add"})
    public String showAddNewsPage(Model model) {
        model.addAttribute("news", new FormDataNews());
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_news_add";
        } else {
            return "root/root_news_add";
        }
    }

    @PostMapping({"/admin/news/add", "/root/news/add"})
    public String saveNews(@ModelAttribute("news") @Valid FormDataNews news,
                           BindingResult bindingResult, Model model) {
        String url = loggedInUser.getUser().getRole().name().toLowerCase();
        if (!bindingResult.hasErrors() && !news.getFile().isEmpty()) {
            newsUtil.saveNews(news);
            return "redirect:/" + url + "/news";
        } else {
            if (news.getFile().isEmpty()) {
                model.addAttribute("message", "Choose image file.");
            }
            model.addAttribute("url", url);
            if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
                return "admin/admin_news_add";
            } else {
                return "root/root_news_add";
            }
        }
    }

    @GetMapping({"/admin/news/{id}", "/root/news/{id}"})
    public String showNews(Model model, @PathVariable Long id) {
        NewsDTO news = newsService.getByIdWithComments(id);
        model.addAttribute("newsObj", news);
        model.addAttribute("newComment", CommentDTO.newBuilder().id(news.getId()).build());
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_news_selected";
        } else {
            return "root/root_news_selected";
        }
    }

    @PostMapping({"/admin/news/{id}", "/root/news/{id}"})
    public String deleteComment(Model model, @PathVariable Long id,
                                HttpServletRequest request) {
        Long commentId = Long.valueOf(request.getParameter("delete"));
        commentService.delete(commentId);
        return "redirect:/" + loggedInUser.getUser().getRole().name().toLowerCase() + "/news/" + id;
    }

    @PostMapping({"/admin/news/{id}/delete", "/root/news/{id}/delete"})
    public String deleteNews(Model model, @PathVariable Long id) {
        newsService.delete(id);
        return "redirect:/" + loggedInUser.getUser().getRole().name().toLowerCase() + "/news/";
    }

    @GetMapping({"/admin/news/{id}/edit", "/root/news/{id}/edit"})
    public String showEditPage(Model model, @PathVariable Long id) {
        FormDataNews dataNews = newsUtil.getForm(id);
        dataNews.setFile(null);
        model.addAttribute("news", dataNews);
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_news_update";
        } else {
            return "root/root_news_update";
        }
    }

    @PostMapping({"/admin/news/{newsId}/edit", "/root/news/{newsId}/edit"})
    public String saveNews(@Valid @ModelAttribute("news") FormDataNews news, BindingResult bindingResult,
                           @PathVariable Long newsId, Model model) {
        String url = loggedInUser.getUser().getRole().name().toLowerCase();
        if (!bindingResult.hasErrors()) {
            newsUtil.updateNews(news);
            return "redirect:/" + url + "/news";
        } else {
            model.addAttribute("url", url);
            if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
                return "admin/admin_news_update";
            } else {
                return "root/root_news_update";
            }
        }
    }
}
