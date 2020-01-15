package com.es.phoneshop.web.controller.util;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItem {

    private static final String WRONG_DATA = "wrong data";

    @NotNull(message = WRONG_DATA)
    @Min(value = 1000L, message = WRONG_DATA)
    private Long phoneId;

    @NotNull(message = WRONG_DATA)
    @Min(value = 1L, message = WRONG_DATA)
    private Long quantity;

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
