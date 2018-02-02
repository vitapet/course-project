package com.gmail.vitaliapetsenak.shop.repository.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.IUserDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements IUserDAO {

    @Override
    public User getByLogin(String login) {
        Criteria criteria = getSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsers(Integer page, Integer count) {
        return getSession().createCriteria(User.class)
                .setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5)
                .list();
    }
}
