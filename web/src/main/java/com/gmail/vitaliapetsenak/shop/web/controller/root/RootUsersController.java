package com.gmail.vitaliapetsenak.shop.web.controller.root;

import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;
import com.gmail.vitaliapetsenak.shop.service.IUserService;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.web.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class RootUsersController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserValidator userValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/root/users")
    public String showUsers(Model model) {
        List<UserDTO> users = userService.getAll(null,null);
        model.addAttribute("users", users);
        return "root/root_users";
    }

    @GetMapping("/root/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        UserDTO userDTO = UserDTO.newBuilder().id(id).build();
        userDTO.setStatus(UserStatus.DELETED);
        userService.delete(userDTO);
        return "redirect:/root/users";
    }

    @GetMapping("/root/users/{id}/block")
    public String blockUser(@PathVariable Long id) {
        UserDTO userDTO = UserDTO.newBuilder().id(id).build();
        userDTO.setStatus(UserStatus.BLOCKED);
        userService.block(userDTO);
        return "redirect:/root/users";
    }

    @GetMapping("/root/users/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        UserDTO userDTO = userService.getById(id);
        model.addAttribute("user", userDTO);
        model.addAttribute("mainRole", UserRole.ROOT);
        List<UserRole> roles = Arrays.asList(UserRole.values());
        List<UserStatus> statuses = Arrays.asList(UserStatus.values());
        model.addAttribute("roles", roles);
        model.addAttribute("statuses", statuses);
        return "root/root_user_update";
    }

    @PostMapping("/root/users/*/edit")
    public String saveUser(@ModelAttribute("user") @Valid UserDTO user,
                           BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            userService.update(user);
            return "redirect:/root/users";
        } else {
            return "root/root_user_update";
        }
    }
}
