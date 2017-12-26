package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.GenericDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends Serializable, PK extends Serializable> implements GenericDAO<T, PK> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final Class<T> entityClass;

    public AbstractDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void beginTransaction() {
        getSession().getTransaction().begin();
    }

    public void commitTransaction() {
        getSession().getTransaction().commit();
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
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }
}
