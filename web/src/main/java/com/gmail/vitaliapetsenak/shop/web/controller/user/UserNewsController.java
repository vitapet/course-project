package com.gmail.vitaliapetsenak.shop.web.controller.user;

import com.gmail.vitaliapetsenak.shop.service.ICommentService;
import com.gmail.vitaliapetsenak.shop.service.INewsService;
import com.gmail.vitaliapetsenak.shop.service.IUserService;
import com.gmail.vitaliapetsenak.shop.service.model.CommentDTO;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateTimeFormatter;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import com.gmail.vitaliapetsenak.shop.web.validator.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

@Controller
public class UserNewsController {

    @Autowired
    private DateTimeFormatter dateTimeFormatter;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private INewsService newsService;

    @Autowired
    private CommentValidator commentValidator;

    @InitBinder("comment")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(commentValidator);
    }

    @GetMapping(value = "/user/news/{id}")
    public String showNews(Model model, @PathVariable Long id) {
        NewsDTO news = newsService.getByIdWithComments(id);
        model.addAttribute("news", news);
        model.addAttribute("newComment", CommentDTO.newBuilder().id(news.getId()).build());
        return "user/user_news";
    }

    @PostMapping("/user/news/{id}")
    public String saveComment(@ModelAttribute("newComment") @Valid CommentDTO comment,
                              BindingResult bindingResult, Model model, @PathVariable Long id) {
        if (!bindingResult.hasErrors()) {
            comment.setTimestamp(dateTimeFormatter.format(Calendar.getInstance().getTime()));
            comment.setUser(userService.getByLogin(getPrincipal().getUsername()));
            commentService.add(comment);
            return "redirect:/user/news/" + comment.getNewsId();
        } else {
            NewsDTO news = newsService.getByIdWithComments(id);
            model.addAttribute("news", news);
            return "user/user_news";
        }
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
