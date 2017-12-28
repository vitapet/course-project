package com.gmail.vitaliapetsenak.shop.service.hibernate.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.UserDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.UserInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.User;
import com.gmail.vitaliapetsenak.shop.service.hibernate.UserService;
import com.gmail.vitaliapetsenak.shop.service.hibernate.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static volatile UserServiceImpl instance;
    private UserInterface userDAO = UserDAO.getInstance();

    private UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<UserDTO> getAll() {
        userDAO.getSession().beginTransaction();
        List<User> users = userDAO.findAll();
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(new UserDTO(user));
        }
        userDAO.getSession().getTransaction().commit();
        return dtoList;
    }

    @Override
    public UserDTO getById(Long id) {
        userDAO.getSession().beginTransaction();
        User user = userDAO.findById(id);
        UserDTO userDTO = new UserDTO(user);
        userDAO.getSession().getTransaction().commit();
        return userDTO;
    }

    @Override
    public UserDTO getByLogin(String login) {
        userDAO.getSession().beginTransaction();
        User user = userDAO.getByLogin(login);
        UserDTO userDTO = new UserDTO(user);
        userDAO.getSession().getTransaction().commit();
        return userDTO;
    }

    @Override
    public Long add(UserDTO userDTO) {
        userDAO.getSession().beginTransaction();
        User user = getUserFromDTO(userDTO);
        userDAO.create(user);
        userDAO.getSession().getTransaction().commit();
        return user.getId();
    }

    @Override
    public void update(UserDTO userDTO) {
        userDAO.getSession().beginTransaction();
        User user = getUserFromDTO(userDTO);
        user.setId(userDTO.getId());
        userDAO.update(user);
        userDAO.getSession().getTransaction().commit();
    }

    @Override
    public void delete(UserDTO userDTO) {
        userDAO.getSession().beginTransaction();
        User user = getUserFromDTO(userDTO);
        user.setId(userDTO.getId());
        userDAO.delete(user);
        userDAO.getSession().getTransaction().commit();
    }

    private User getUserFromDTO(UserDTO userDTO) {
        User user = new User();
        Converter.convert(userDTO, user);
        return user;
    }
}
