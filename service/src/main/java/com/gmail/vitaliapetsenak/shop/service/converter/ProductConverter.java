package com.gmail.vitaliapetsenak.shop.service.converter;

import com.gmail.vitaliapetsenak.shop.repository.model.Product;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    public Product convert(ProductDTO productDTO) {
        Product product = new Product();
        if (productDTO.getId() != null) {
            product.setId(productDTO.getId());
        }
        if (productDTO.getName() != null) {
            product.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            product.setDescription(productDTO.getDescription());
        }
        if (productDTO.getCategory() != null) {
            product.setCategory(productDTO.getCategory());
        }
        if (productDTO.getCount() != null) {
            product.setCount(productDTO.getCount());
        }
        if (productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }
        if (productDTO.getStatus() != null) {
            product.setStatus(productDTO.getStatus());
        }
        return product;
    }

    public List<ProductDTO> productsToDTO(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        if (products != null) {
            for (Product product : products) {
                productDTOS.add(new ProductDTO(product));
            }
            return productDTOS;
        }
        return null;
    }
}
