package com.gmail.vitaliapetsenak.shop.repository.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T extends Serializable, PK extends Serializable> {
    T findById(PK id);

    List<T> findAll();

    PK create(T entity);

    void update(T entity);

    void delete(T entity);
}
