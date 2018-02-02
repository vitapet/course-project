package com.gmail.vitaliapetsenak.shop.service.impl;


import com.gmail.vitaliapetsenak.shop.repository.dao.IProductIDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.Product;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductSearchForm;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductStatus;
import com.gmail.vitaliapetsenak.shop.service.IProductService;
import com.gmail.vitaliapetsenak.shop.service.converter.ProductConverter;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductIDAO productDAO;
    @Autowired
    private ProductConverter productConverter;

    @Override
    @Transactional
    public List<ProductDTO> getAll(Integer page, Integer count) {
        return productConverter.productsToDTO(productDAO.getAll(page, count));
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllNotDeleted(Integer page, Integer count) {
        return productConverter.productsToDTO(productDAO.getNotDeleted(page, count));
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllByCategory(ProductCategory category, Integer page, Integer count) {
        return getProductDTOS(category, null, page, count);
    }

    @Override
    @Transactional
    public List<ProductDTO> getAllNotDeletedByCategory(ProductCategory category, Integer page, Integer count) {
        return getProductDTOS(category, ProductStatus.NOT_DELETED, page, count);
    }

    @Override
    @Transactional
    public List<ProductDTO> searchResult(ProductSearchForm productSearchForm, Integer page, Integer count) {
        List<Product> products = productDAO.searchResult(productSearchForm, page, count);
        List<ProductDTO> productDTOList = productConverter.productsToDTO(products);
        return productDTOList;
    }

    @Override
    @Transactional
    public Long add(ProductDTO productDTO) {
        Product product = productConverter.convert(productDTO);
        productDAO.create(product);
        return product.getId();
    }

    @Override
    @Transactional
    public void delete(ProductDTO productDTO) {
        Product product = productDAO.findById(productDTO.getId());
        if (product.getOrderProducts().isEmpty()) {
            productDAO.delete(product);
        } else {
            product.setStatus(ProductStatus.DELETED);
            productDAO.update(product);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = productDAO.findById(id);
        if (product.getOrderProducts().isEmpty()) {
            productDAO.delete(product);
        } else {
            product.setStatus(ProductStatus.DELETED);
            productDAO.update(product);
        }
    }

    @Override
    @Transactional
    public void update(ProductDTO productDTO) {
        Product product = productConverter.convert(productDTO);
        productDAO.update(product);
    }

    @Override
    @Transactional
    public ProductDTO getById(Long id) {
        return new ProductDTO(productDAO.findById(id));
    }

    private List<ProductDTO> getProductDTOS(ProductCategory category, ProductStatus status, Integer page, Integer count) {
        List<Product> products;
        if (status != null) {
            products = productDAO.getByCategory(category, status, page, count);
        } else {
            products = productDAO.getByCategory(category, page, count);
        }
        return productConverter.productsToDTO(products);
    }
}
