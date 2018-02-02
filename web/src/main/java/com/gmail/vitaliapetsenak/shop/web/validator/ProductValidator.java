package com.gmail.vitaliapetsenak.shop.web.validator;

import com.gmail.vitaliapetsenak.shop.web.model.ProductForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "product.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "product.description.empty");
        ValidationUtils.rejectIfEmpty(errors, "count", "product.count.empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "product.price.empty");

        ProductForm product = (ProductForm) target;

        Pattern pattern;

        if (!product.getName().isEmpty() && product.getName() != null) {
            pattern = Pattern.compile("^[a-zA-Z0-9.\\s-]{1,50}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(product.getName()).matches())) {
                errors.rejectValue("name", "product.name.invalid");
            }
        }
        if (!product.getDescription().isEmpty() && product.getDescription() != null) {
            pattern = Pattern.compile("^[a-zA-Z0-9.\\s-]{1,250}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(product.getDescription()).matches())) {
                errors.rejectValue("description", "product.description.invalid");
            }
        }
        if (!product.getCount().isEmpty() && product.getCount() != null) {
            pattern = Pattern.compile("^[1-9][0-9]{0,8}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(product.getCount()).matches())) {
                errors.rejectValue("count", "product.count.invalid");
            }
        }
        if (!product.getPrice().isEmpty() && product.getPrice() != null) {
            pattern = Pattern.compile("(^0.)|(^[1-9]{1,8}.)[0-9]{2}$",
                    Pattern.CASE_INSENSITIVE);
            if (!(pattern.matcher(product.getPrice()).matches())) {
                errors.rejectValue("price", "product.price.invalid");
            }
        }
    }
}
