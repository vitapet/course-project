package com.gmail.vitaliapetsenak.shop.web.util;


import com.gmail.vitaliapetsenak.shop.service.IProductService;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;
import com.gmail.vitaliapetsenak.shop.web.model.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductUtil {
    @Autowired
    private IProductService productService;

    public void addProduct(ProductForm productForm) {
        ProductDTO product = new ProductDTO();
        parseProductForm(productForm, product);
        productService.add(product);
    }

    public void updateProduct(ProductForm productForm) {
        ProductDTO product = new ProductDTO();
        parseProductForm(productForm, product);
        productService.update(product);
    }

    private void parseProductForm(ProductForm productForm, ProductDTO product) {
        product.setId(productForm.getId());
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setCategory(productForm.getCategory());
        product.setCount(Integer.valueOf(productForm.getCount()));
        product.setPrice(new BigDecimal(productForm.getPrice()));
    }
}
