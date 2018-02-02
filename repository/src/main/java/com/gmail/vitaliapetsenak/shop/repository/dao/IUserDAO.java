package com.gmail.vitaliapetsenak.shop.repository.dao;


import com.gmail.vitaliapetsenak.shop.repository.model.User;

import java.util.List;

public interface IUserDAO extends IGenericDAO<User, Long> {
    User getByLogin(String login);
    List<User> getUsers(Integer page, Integer count);
}
