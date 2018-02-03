package com.gmail.vitaliapetsenak.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "access_denied_page";
    }
}
