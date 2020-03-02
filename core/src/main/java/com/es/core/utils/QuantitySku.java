package com.es.core.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuantitySkuValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuantitySku {
    String message() default "Invalid quantity sku";
    Class<?>[]groups() default {};
    Class<? extends Payload>[]payload() default {};
}
