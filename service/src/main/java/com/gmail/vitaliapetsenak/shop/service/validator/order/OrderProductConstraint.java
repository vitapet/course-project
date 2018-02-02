package com.gmail.vitaliapetsenak.shop.service.validator.order;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderProductValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderProductConstraint {
    String message() default "Invalid count.";
}
