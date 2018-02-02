package com.gmail.vitaliapetsenak.shop.service;

import com.gmail.vitaliapetsenak.shop.repository.model.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductSearchForm;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAll(Integer page, Integer count);

    List<ProductDTO> getAllNotDeleted(Integer page, Integer count);

    List<ProductDTO> getAllByCategory(ProductCategory category, Integer page, Integer count);

    List<ProductDTO> getAllNotDeletedByCategory(ProductCategory category, Integer page, Integer count);

    List<ProductDTO> searchResult(ProductSearchForm productSearchForm, Integer page, Integer count);

    Long add(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    void delete(Long id);

    void update(ProductDTO productDTO);

    ProductDTO getById(Long id);
}
