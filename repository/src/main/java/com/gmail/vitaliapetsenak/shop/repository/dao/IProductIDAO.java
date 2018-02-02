package com.gmail.vitaliapetsenak.shop.repository.dao;


import com.gmail.vitaliapetsenak.shop.repository.model.*;

import java.util.List;

public interface IProductIDAO extends IGenericDAO<Product, Long> {
    List<Product> getByCategory(ProductCategory category, ProductStatus status, Integer page, Integer count);

    List<Product> getByCategory(ProductCategory category, Integer page, Integer count);

    List<Product> getNotDeleted(Integer page, Integer count);

    List<Product> getAll(Integer page, Integer count);

    List<OrderProduct> getOrderProductById(Long id);

    List<Product> searchResult(ProductSearchForm productSearchForm,Integer page, Integer count);
}
