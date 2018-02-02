package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll(Integer page, Integer count);

    UserDTO getById(Long id);

    UserDTO getByLogin(String login);

    Long add(UserDTO userDTO);

    void update(UserDTO userDTO);

    void delete(UserDTO userDTO);

    @Transactional
    void block(UserDTO userDTO);
}
