package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.GenericDAO;

import java.io.Serializable;
import java.util.List;

public class AbstractDAO<T extends Serializable, PK extends Serializable> implements GenericDAO<T, PK> {
    @Override
    public T findById(PK id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public PK create(T entity) {
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(PK id) {

    }
}
