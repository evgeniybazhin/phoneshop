package com.es.core.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class QuantitySkuValidator implements ConstraintValidator<QuantitySku, Map<Long, Long>> {
    @Override
    public void initialize(QuantitySku quantitySku) {

    }

    @Override
    public boolean isValid(Map<Long, Long> itemsToUpdate, ConstraintValidatorContext constraintValidatorContext) {
        if(itemsToUpdate == null || itemsToUpdate.isEmpty()){
            return false;
        }

        for(Map.Entry<Long, Long> item : itemsToUpdate.entrySet()){
            return item.getValue().toString().matches("[0-9]+");
        }
        return false;
    }
}
