package com.gmail.vitaliapetsenak.shop.web.controller;

import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;
import com.gmail.vitaliapetsenak.shop.service.IUserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.validator.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping
    public String getRegistrationPage(ModelMap map) {
        UserDTO user = UserDTO.newBuilder().build();
        map.addAttribute("userDTO", user);
        return "registration";
    }

    @PostMapping
    public String submitRegistration(@ModelAttribute("userDTO") @Valid UserDTO user,
                                     BindingResult bindingResult, ModelMap model) throws ParseException {
        if (!bindingResult.hasErrors()) {
            UserDTO userCheck = userService.getByLogin(user.getLogin());
            if (userCheck == null) {
                user.setRole(UserRole.USER);
                user.setStatus(UserStatus.ACTIVE);
                userService.add(user);
                logger.info("Success registration: " + user.getLogin());
                return "redirect:/login";
            } else {
                model.addAttribute("message", "User with the same login already exists.");
                return "registration";
            }
        } else {
            return "registration";
        }
    }
}
