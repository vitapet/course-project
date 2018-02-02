package com.gmail.vitaliapetsenak.shop.web.controller.admin;

import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.web.model.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/admin", "/root"})
public class AdminController {

    @Autowired
    private LoggedInUser loggedInUser;

    @RequestMapping(method = RequestMethod.GET)
    public String showAdminPage(Model model) {
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_page";
        } else {
            return "root/root_page";
        }
    }
}
