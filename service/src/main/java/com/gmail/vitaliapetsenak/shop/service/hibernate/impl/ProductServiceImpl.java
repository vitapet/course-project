package com.gmail.vitaliapetsenak.shop.service.hibernate.impl;


import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl.ProductDAO;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.ProductInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Product;
import com.gmail.vitaliapetsenak.shop.service.hibernate.ProductService;
import com.gmail.vitaliapetsenak.shop.service.hibernate.converter.Converter;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.ProductCategoryDTO;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.ProductDTO;
import com.gmail.vitaliapetsenak.shop.service.hibernate.model.ProductStatusDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static volatile ProductServiceImpl instance;
    private ProductInterface productDAO = ProductDAO.getInstance();

    private ProductServiceImpl() {
    }

    public static synchronized ProductServiceImpl getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    @Override
    public List<ProductDTO> getAll() {
        productDAO.getSession().beginTransaction();
        List<Product> products = productDAO.findAll();
        List<ProductDTO> dtoList = getProductDTOList(products);
        productDAO.getSession().getTransaction().commit();
        return dtoList;
    }

    @Override
    public List<ProductDTO> getAllNotDeleted() {
        productDAO.getSession().beginTransaction();
        List<Product> products = productDAO.getNotDeleted();
        List<ProductDTO> dtoList = getProductDTOList(products);
        productDAO.getSession().getTransaction().commit();
        return dtoList;
    }

    @Override
    public List<ProductDTO> getAllByCategory(ProductCategoryDTO category) {
        productDAO.getSession().beginTransaction();
        List<ProductDTO> dtoList = getProductDTOS(category, null);
        productDAO.getSession().getTransaction().commit();
        return dtoList;
    }

    @Override
    public List<ProductDTO> getAllNotDeletedByCategory(ProductCategoryDTO category) {
        productDAO.getSession().beginTransaction();
        List<ProductDTO> dtoList = getProductDTOS(category, ProductStatusDTO.NOT_DELETED);
        productDAO.getSession().getTransaction().commit();
        return dtoList;
    }

    @Override
    public Long add(ProductDTO productDTO) {
        productDAO.getSession().beginTransaction();
        Product product = getProductFromDTO(productDTO);
        productDAO.create(product);
        productDAO.getSession().getTransaction().commit();
        return product.getId();
    }

    @Override
    public void delete(ProductDTO productDTO) {
        productDAO.getSession().beginTransaction();
        Product product = getProductFromDTO(productDTO);
        product.setId(productDTO.getId());
        productDAO.delete(product);
        productDAO.getSession().getTransaction().commit();
    }

    @Override
    public void update(ProductDTO productDTO) {
        productDAO.getSession().beginTransaction();
        Product product = getProductFromDTO(productDTO);
        product.setId(productDTO.getId());
        productDAO.update(product);
        productDAO.getSession().getTransaction().commit();
    }

    private List<ProductDTO> getProductDTOList(List<Product> products) {
        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product : products) {
            dtoList.add(new ProductDTO(product));
        }
        return dtoList;
    }

    private List<ProductDTO> getProductDTOS(ProductCategoryDTO category, ProductStatusDTO status) {
        List<Product> products = null;
        if (status != null) {
            products = productDAO.getByCategory(category.getCategory(), status.getStatus());
        } else {
            products = productDAO.getByCategory(category.getCategory());
        }
        return getProductDTOList(products);
    }

    private Product getProductFromDTO(ProductDTO productDTO) {
        Product product = new Product();
        Converter.convert(productDTO, product);
        return product;
    }
}
