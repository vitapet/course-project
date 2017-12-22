package com.gmail.vitaliapetsenak.shop.repository.dao.interfaces;

import java.util.List;

public interface GenericDAO<T> {
    List<T> readAll();

    T read(T t);

    T readById(Long id);

    void create(T t);

    void update(T t);

    void delete(Long id);
}
