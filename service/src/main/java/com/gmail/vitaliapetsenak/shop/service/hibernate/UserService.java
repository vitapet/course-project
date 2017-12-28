package com.gmail.vitaliapetsenak.shop.service.hibernate;

import com.gmail.vitaliapetsenak.shop.service.hibernate.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();

    UserDTO getById(Long id);

    UserDTO getByLogin(String login);

    Long add(UserDTO userDTO);

    void update(UserDTO userDTO);

    void delete(UserDTO userDTO);
}
