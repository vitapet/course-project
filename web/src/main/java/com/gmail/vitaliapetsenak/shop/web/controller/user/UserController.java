package com.gmail.vitaliapetsenak.shop.web.controller.user;

import com.gmail.vitaliapetsenak.shop.service.INewsService;
import com.gmail.vitaliapetsenak.shop.service.model.NewsDTO;
import com.gmail.vitaliapetsenak.shop.web.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes(types = ShoppingCart.class)
public class UserController {

    @Autowired
    private INewsService newsService;


    @GetMapping(value = "/user")
    public String getUserPage(ModelMap model) {
        List<NewsDTO> newsList = newsService.getAll(null,null);
        model.addAttribute("newsList", newsList);
        return "user/user_page";
    }
}
