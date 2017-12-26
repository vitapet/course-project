package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Product;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.ProductStatus;

import java.util.List;

public interface ProductInterface extends GenericDAO<Product, Long> {
    List<Product> getByCategory(ProductCategory category, ProductStatus status);

    List<Product> getByCategory(ProductCategory category);

    List<Product> getNotDeleted();
}
