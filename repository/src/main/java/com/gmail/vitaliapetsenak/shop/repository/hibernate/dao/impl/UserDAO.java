package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.UserInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.User;

public class UserDAO extends AbstractDAO<User, Long> implements UserInterface {
    private static volatile UserDAO instance;

    private UserDAO() {
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
}
