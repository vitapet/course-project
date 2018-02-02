package com.gmail.vitaliapetsenak.shop.web.controller;

import com.gmail.vitaliapetsenak.shop.service.IUserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @GetMapping
    public String getLoginPage(Model map, HttpServletRequest request) {
        UserDTO user = UserDTO.newBuilder().build();
        if (request.getParameter("error") != null) {
            map.addAttribute("error", true);
        }
        map.addAttribute("user", user);
        return "login";
    }
}
