package com.es.core.cart;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItemDTO {
    @NotNull
    private Long phoneId;

    @NotNull
    @Min(1)
    private Long quantity;

    public CartItemDTO() {
    }

    public CartItemDTO(Long phoneId, Long quantity) {
        this.phoneId = phoneId;
        this.quantity = quantity;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getPhoneId() {
        return phoneId;
    }
}
