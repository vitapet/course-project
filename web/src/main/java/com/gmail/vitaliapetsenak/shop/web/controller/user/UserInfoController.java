package com.gmail.vitaliapetsenak.shop.web.controller.user;

import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;
import com.gmail.vitaliapetsenak.shop.service.IUserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.model.UserPrincipal;
import com.gmail.vitaliapetsenak.shop.web.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/info")
public class UserInfoController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping
    public String getUserInfo(ModelMap model) {
        UserDTO user = userService.getByLogin(getPrincipal().getUsername());
        model.addAttribute("userDTO", user);
        return "user/user_info";
    }

    @PostMapping
    public String saveUserInfo(@ModelAttribute("userDTO") @Valid UserDTO user,
                               BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            UserDTO userCheck = userService.getByLogin(user.getLogin());
            if (userCheck == null || userCheck.getLogin().equals(getPrincipal().getUsername())) {
                user.setId(getPrincipal().getUserId());
                user.setRole(getPrincipal().getRole());
                user.setStatus(UserStatus.ACTIVE);
                userService.update(user);
                return "redirect:/user";
            } else {
                model.addAttribute("message", "User with the same login already exists.");
                return "user/user_info";
            }
        } else {
            return "user/user_info";
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
