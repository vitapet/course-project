package com.gmail.vitaliapetsenak.shop.repository.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.IGenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDAOImpl<T extends Serializable, PK extends Serializable> implements IGenericDAO<T, PK> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> entityClass;

    public GenericDAOImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T findById(PK id) {
        T t = getSession().get(this.entityClass, id);
        return t;
    }

    @Override
    public List<T> findAll() {
        List<T> list = getSession().createCriteria(this.entityClass).list();
        return list;
    }

    @Override
    public PK create(T entity) {
        PK pk = (PK) getSession().save(entity);
        return pk;
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }
}
