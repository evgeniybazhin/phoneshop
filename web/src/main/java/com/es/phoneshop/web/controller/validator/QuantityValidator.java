package com.es.phoneshop.web.controller.validator;

import com.es.core.cart.CartItemDTOWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Map;

@Service
@PropertySource("classpath:message.properties")
public class QuantityValidator implements Validator {
    private static final String QUANTITY_NULL_CODE = "quantity.null";

    private static final String QUANTITY_NEGATIVE_CODE = "quantity.null";

    private static final String PATH = "itemsForUpdate['%s']";

    @Value("${quantity.null}")
    private String quantityNull;

    @Value("${quantity.negative}")
    private String quantityNegative;

    @Override
    public boolean supports(Class<?> aClass) {
        return CartItemDTOWrapper.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CartItemDTOWrapper carItems = (CartItemDTOWrapper) o;
        for(Map.Entry<Long, Long> item : carItems.getItemsForUpdate().entrySet()){
            Long phoneId = item.getKey();
            Long quantity = item.getValue();
            validateQuantity(phoneId, quantity, errors);
        }
    }

    private boolean validateQuantity(Long phoneId, Long quantity, Errors errors) {
        boolean isCorrect = true;
        if (quantity == null) {
            errors.rejectValue(String.format(PATH, phoneId), QUANTITY_NULL_CODE, quantityNull);
            isCorrect = false;
        }
        if (quantity != null && quantity < 1) {
            errors.rejectValue(String.format(PATH, phoneId), QUANTITY_NEGATIVE_CODE, quantityNegative);
            isCorrect = false;
        }
        return isCorrect;
    }
}
