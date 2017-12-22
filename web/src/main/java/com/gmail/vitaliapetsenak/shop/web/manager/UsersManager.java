package com.gmail.vitaliapetsenak.shop.web.manager;

import com.gmail.vitaliapetsenak.shop.service.UserService;
import com.gmail.vitaliapetsenak.shop.service.model.RoleDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.service.model.UserStatusDTO;
import com.gmail.vitaliapetsenak.shop.web.model.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class UsersManager {
    private static UserService userService = UserService.getInstance();

    public static void putListToRequest(HttpServletRequest request) {
        List<UserDTO> users = userService.getAllUsers();
        request.setAttribute("users", users);
    }

    public static void deleteUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id == null) {
            request.getSession().setAttribute("message", "Choose user.");
        } else {
            userService.deleteUser(Long.valueOf(id));
            request.getSession().setAttribute("message", "User was removed successfully.");
        }
    }

    public static void putUserToRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        UserDTO user = userService.getUser(Long.valueOf(id));
        List<RoleDTO> roleList = Arrays.asList(RoleDTO.values());
        List<UserStatusDTO> statusList = Arrays.asList(UserStatusDTO.values());
        request.setAttribute("userObj", user);
        request.setAttribute("roleList", roleList);
        request.setAttribute("statusList", statusList);
    }

    public static void updateUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        UserDTO user = UserDTO.newBuilder()
                .id(Long.valueOf(id))
                .password(request.getParameter("password"))
                .role(RoleDTO.valueOf(request.getParameter("userRole")))
                .status(UserStatusDTO.valueOf(request.getParameter("userStatus")))
                .build();
        userService.updateUserInfo(user);
        request.getSession().setAttribute("message", "User was updated successfully");
    }

    public static UserDTO parseRequest(HttpServletRequest request, Command command) {
        UserDTO user = UserDTO.newBuilder()
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .firstName(request.getParameter("firstName"))
                .surname(request.getParameter("surname"))
                .birthDate(Date.valueOf(request.getParameter("birthDate")))
                .phone(request.getParameter("phone"))
                .country(request.getParameter("country"))
                .city(request.getParameter("city"))
                .street(request.getParameter("street"))
                .house(request.getParameter("house"))
                .block(Integer.valueOf(request.getParameter("block")))
                .apartment(Integer.valueOf(request.getParameter("apartment")))
                .build();
        switch (command) {
            case ADD:
                user.setRole(RoleDTO.USER);
                user.setStatus(UserStatusDTO.ACTIVE);
        }
        return user;
    }
}
