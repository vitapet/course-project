package com.gmail.vitaliapetsenak.shop.service.hibernate;

import com.gmail.vitaliapetsenak.shop.service.hibernate.model.ProductCategoryDTO;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();

    List<ProductDTO> getAllNotDeleted();

    List<ProductDTO> getAllByCategory(ProductCategoryDTO category);

    List<ProductDTO> getAllNotDeletedByCategory(ProductCategoryDTO category);

    Long add(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    void update(ProductDTO productDTO);
}
