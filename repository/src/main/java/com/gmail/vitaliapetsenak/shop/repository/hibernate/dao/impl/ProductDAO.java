package com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.hibernate.dao.interfaces.ProductInterface;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.Product;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.hibernate.pojo.ProductStatus;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product, Long> implements ProductInterface {

    private static volatile ProductDAO instance;

    private ProductDAO() {
    }

    public static synchronized ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getByCategory(ProductCategory category, ProductStatus status) {
        List<Product> products = getSession().createCriteria(Product.class)
                .add(Restrictions.eq("F_CATEGORY", category))
                .add(Restrictions.eq("F_STATUS", status))
                .list();
        return products;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getByCategory(ProductCategory category) {
        List<Product> products = getSession().createCriteria(Product.class)
                .add(Restrictions.eq("F_CATEGORY", category))
                .list();
        return products;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getNotDeleted() {
        List<Product> products = getSession().createCriteria(Product.class)
                .add(Restrictions.eq("F_STATUS", ProductStatus.NOT_DELETED))
                .list();
        return products;
    }
}
