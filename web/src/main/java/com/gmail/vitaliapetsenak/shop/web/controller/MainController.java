package com.gmail.vitaliapetsenak.shop.web.controller;

import com.gmail.vitaliapetsenak.shop.web.model.LoggedInUser;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private LoggedInUser loggedInUser;

    @GetMapping
    public String getWelcomePage(Model model) {
        if (getPrincipal() != null) {
            return "redirect:/" + getPrincipal().getRole().name().toLowerCase();
        }
        return "redirect:/news/page/" + 1;
    }

    private UserPrincipal getPrincipal() {
        UserPrincipal user = null;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal != null) {
                user = principal;
            }
        }
        return user;
    }
}
