package com.gmail.vitaliapetsenak.shop.service.validator.order;

import com.gmail.vitaliapetsenak.shop.service.model.OrderProductDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class OrderProductValidator implements ConstraintValidator<OrderProductConstraint, List<OrderProductDTO>> {

    @Override
    public void initialize(OrderProductConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<OrderProductDTO> value, ConstraintValidatorContext context) {
        for (OrderProductDTO orderProduct : value) {
            if (orderProduct.getCount() == null || orderProduct.getCount() == 0) {
                return false;
            }
        }
        return true;
    }
}
