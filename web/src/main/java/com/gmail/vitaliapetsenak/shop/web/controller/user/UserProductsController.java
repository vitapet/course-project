package com.gmail.vitaliapetsenak.shop.web.controller.user;

import com.gmail.vitaliapetsenak.shop.repository.model.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.ProductSearchForm;
import com.gmail.vitaliapetsenak.shop.service.IProductService;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;
import com.gmail.vitaliapetsenak.shop.web.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user/catalog")
public class UserProductsController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/{category}")
    public String showCatalog(Model model, @PathVariable String category) {
        List<ProductDTO> products;
        if (category.toLowerCase().equals("all")) {
            products = productService.getAllNotDeleted(null, 10);
        } else {
            products = productService.getAllNotDeletedByCategory(ProductCategory.valueOf(category), null, 10);
        }
        model.addAttribute("products", products);
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        model.addAttribute("categories", categories);
        model.addAttribute("curCategory", category.toLowerCase());
        model.addAttribute("searchForm", new ProductSearchForm());
        return "user/user_catalog";
    }

    @PostMapping("/{category}")
    public String showSearchResultCatalog(Model model, @PathVariable String category,
                                          @ModelAttribute ProductSearchForm searchForm) {
        List<ProductDTO> products;

        if (category.toLowerCase().equals("all")) {
            searchForm.setCategory(null);
            products = productService.searchResult(searchForm, null, null);
        } else {
            searchForm.setCategory(ProductCategory.valueOf(category));
            products = productService.searchResult(searchForm, null, null);
        }
        model.addAttribute("products", products);
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        model.addAttribute("categories", categories);
        model.addAttribute("curCategory", category.toLowerCase());
        model.addAttribute("searchForm", searchForm);
        return "user/user_catalog";
    }

    @GetMapping("/cart/add/{curCategory}/{productId}")
    public String addToShopCart(@PathVariable Long productId,
                                @PathVariable String curCategory) {
        shoppingCart.addToCart(productId);
        return "redirect:/user/catalog/" + curCategory;
    }
}
