package com.gmail.vitaliapetsenak.shop.web.controller.admin;

import com.gmail.vitaliapetsenak.shop.repository.model.ProductCategory;
import com.gmail.vitaliapetsenak.shop.repository.model.UserRole;
import com.gmail.vitaliapetsenak.shop.service.IProductService;
import com.gmail.vitaliapetsenak.shop.service.model.ProductDTO;
import com.gmail.vitaliapetsenak.shop.web.model.LoggedInUser;
import com.gmail.vitaliapetsenak.shop.web.model.ProductForm;
import com.gmail.vitaliapetsenak.shop.web.util.ProductUtil;
import com.gmail.vitaliapetsenak.shop.web.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminProductsController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private ProductUtil productUtil;
    @Autowired
    private LoggedInUser loggedInUser;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(productValidator);
    }

    @GetMapping({"/admin/catalog/{category}", "/root/catalog/{category}"})
    public String getProducts(Model model, @PathVariable String category) {
        List<ProductDTO> products;
        if (category.toLowerCase().equals("all")) {
            products = productService.getAll(null, null);
        } else {
            products = productService.getAllByCategory(ProductCategory.valueOf(category), null, null);
        }
        model.addAttribute("products", products);
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        model.addAttribute("categories", categories);
        model.addAttribute("curCategory", category.toLowerCase());
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_catalog";
        } else {
            return "root/root_catalog";
        }
    }

    @GetMapping({"/admin/catalog/{id}/delete", "/root/catalog/{id}/delete"})
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/" + loggedInUser.getUser().getRole().name().toLowerCase() + "/catalog/all";
    }

    @GetMapping({"/admin/catalog/{id}/update", "/root/catalog/{id}/update"})
    public String getUpdatePage(@PathVariable Long id,
                                Model model) {
        ProductDTO productDTO = productService.getById(id);
        ProductForm product = new ProductForm(productDTO);
        model.addAttribute("product", product);
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        model.addAttribute("categories", categories);
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_product";
        } else {
            return "root/root_product";
        }
    }

    @GetMapping({"/admin/catalog/add", "/root/catalog/add"})
    public String getAddPage(Model model) {
        ProductForm product = new ProductForm();
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_product";
        } else {
            return "root/root_product";
        }
    }

    @PostMapping({"/admin/catalog/*/update", "/admin/catalog/add", "/admin/catalog/*/copy",
            "/root/catalog/*/update", "/root/catalog/add", "/root/catalog/*/copy"})
    public String saveProduct(@ModelAttribute("product") @Valid ProductForm product,
                              BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (product.getId() == null) {
                productUtil.addProduct(product);
            } else {
                productUtil.updateProduct(product);
            }
            return "redirect:/" + loggedInUser.getUser().getRole().name().toLowerCase() + "/catalog/all";
        } else {
            if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
                return "admin/admin_product";
            } else {
                return "root/root_product";
            }
        }
    }

    @GetMapping({"/admin/catalog/{copiedId}/copy", "/root/catalog/{copiedId}/copy"})
    public String getAddPageAfterCopy(Model model, @PathVariable("copiedId") Long copiedId) {
        ProductDTO productDTO = new ProductDTO();
        if (copiedId != null) {
            productDTO = productService.getById(copiedId);
        }
        ProductForm product = new ProductForm(productDTO);
        product.setId(null);
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("url", loggedInUser.getUser().getRole().name().toLowerCase());
        if (loggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return "admin/admin_product";
        } else {
            return "root/root_product";
        }
    }
}
