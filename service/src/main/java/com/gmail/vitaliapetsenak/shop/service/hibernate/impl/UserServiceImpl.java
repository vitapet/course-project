package com.gmail.vitaliapetsenak.shop.service.hibernate.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.UserDAOImpl;
import com.gmail.vitaliapetsenak.shop.repository.dao.interfaces.UserInterface;
import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;
import com.gmail.vitaliapetsenak.shop.service.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl {
    private static volatile UserServiceImpl instance;
    private UserInterface userDAO = UserDAOImpl.getInstance();

    private UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = userDAO.readAll();
        for (User user : userList) {
            userDTOList.add(new UserDTO(user));
        }
        return userDTOList;
    }

    public UserDTO getUser(Long id) {
        User user = userDAO.readById(id);
        if (user != null) {
            return new UserDTO(user);
        }
        return null;
    }

    public UserDTO getUser(String login) {
        User user = User.newBuilder()
                .login(login)
                .build();
        user = userDAO.read(user);
        if (user != null) {
            return new UserDTO(user);
        }
        return null;
    }

    public void updateUserInfo(UserDTO userDTO) {
        User user = userDAO.readById(userDTO.getId());
        Converter.convert(userDTO, user);
        userDAO.update(user);
    }

    public void addNewUser(UserDTO userDTO) {
        User user = User.newBuilder().build();
        Converter.convert(userDTO, user);
        userDAO.create(user);
    }

    public void deleteUser(Long id) {
        User user = userDAO.readById(id);
        user.setStatus(UserStatus.DELETED);
        userDAO.update(user);
//        userDAO.delete(id);
    }
}
