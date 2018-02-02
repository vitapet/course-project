package com.gmail.vitaliapetsenak.shop.repository.dao.impl;

import com.gmail.vitaliapetsenak.shop.repository.dao.IProductIDAO;
import com.gmail.vitaliapetsenak.shop.repository.model.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository("productDAO")
public class ProductDAOImpl extends GenericDAOImpl<Product, Long> implements IProductIDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getByCategory(ProductCategory category, ProductStatus status,
                                       Integer page, Integer count) {
        return (List<Product>) getSession().createCriteria(Product.class)
                .add(Restrictions.eq("category", category))
                .add(Restrictions.eq("status", status))
                .setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getByCategory(ProductCategory category,
                                       Integer page, Integer count) {
        return (List<Product>) getSession().createCriteria(Product.class)
                .add(Restrictions.eq("category", category))
                .setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getNotDeleted(Integer page, Integer count) {
        return (List<Product>) getSession().createCriteria(Product.class)
                .add(Restrictions.eq("status", ProductStatus.NOT_DELETED))
                .setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getAll(Integer page, Integer count) {
        return (List<Product>) getSession().createCriteria(Product.class)
                .setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderProduct> getOrderProductById(Long id) {
        List<OrderProduct> orderProducts = getSession().createCriteria(OrderProduct.class)
                .add(Restrictions.eq("primaryKey.product.id", id))
                .list();
        return orderProducts;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> searchResult(ProductSearchForm productSearchForm, Integer page, Integer count) {
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq("status", ProductStatus.NOT_DELETED));
        if (productSearchForm.getName() != null && !productSearchForm.getName().isEmpty()) {
            StringBuilder nameAttr = new StringBuilder("%").append(productSearchForm.getName()).append("%");
            criteria.add(Restrictions.ilike("name", nameAttr));
        }
        if (productSearchForm.getPrice() != null && !productSearchForm.getPrice().isEmpty()) {
            BigDecimal price = new BigDecimal(productSearchForm.getPrice());
            criteria.add(Restrictions.ge("price", price));
        }
        if (productSearchForm.getCategory() != null) {
            criteria.add(Restrictions.eq("category", productSearchForm.getCategory()));
        }
        criteria.setFirstResult(page != null ? page : 0)
                .setMaxResults(count != null ? count : 5);
        return (List<Product>) criteria.list();
    }
}
