package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.UserInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.User;
import org.hibernate.criterion.Restrictions;

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

    @Override
    public User getByLogin(String login) {
        User user = (User) getSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login));
        return user;
    }
}
