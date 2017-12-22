package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T extends Serializable, PK extends Serializable> {
    T findById(PK id);

    List<T> findAll();

    PK create(T entity);

    void update(T entity);

    void delete(PK id);
}