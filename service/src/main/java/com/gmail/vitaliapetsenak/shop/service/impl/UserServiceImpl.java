package com.gmail.vitaliapetsenak.shop.service.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.IUserDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;
import com.gmail.vitaliapetsenak.shop.service.IUserService;
import com.gmail.vitaliapetsenak.shop.service.converter.UserConverter;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<UserDTO> getAll(Integer page, Integer count) {
        return userConverter.usersToDTO(userDAO.getUsers(page, count));
    }

    @Override
    @Transactional
    public UserDTO getById(Long id) {
        User user = userDAO.findById(id);
        if (user == null) {
            return null;
        }
        return userConverter.convertToDTO(user);
    }

    @Override
    @Transactional
    public UserDTO getByLogin(String login) {
        User user = userDAO.getByLogin(login);
        if (user == null) {
            return null;
        }
        return userConverter.convertToDTO(user);
    }

    @Override
    @Transactional
    public Long add(UserDTO userDTO) {
        User user = new User();
        try {
            userConverter.convert(user, userDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.create(user);
            return user.getId();
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User user = userDAO.findById(userDTO.getId());
        try {
            userConverter.convert(user, userDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.update(user);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void delete(UserDTO userDTO) {
        User user = userDAO.findById(userDTO.getId());
        if (user.getComments().isEmpty() && user.getNews().isEmpty()
                && user.getOrders().isEmpty()) {
            userDAO.delete(user);
        } else {
            user.setStatus(UserStatus.DELETED);
            userDAO.update(user);
        }
    }

    @Override
    @Transactional
    public void block(UserDTO userDTO) {
        User user = userDAO.findById(userDTO.getId());
        user.setStatus(UserStatus.BLOCKED);
    }
}
