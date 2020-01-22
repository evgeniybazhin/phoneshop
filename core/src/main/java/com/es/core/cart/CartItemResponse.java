package com.es.core.cart;

import com.es.core.model.phone.Phone;

import java.math.BigDecimal;

public class CartItemResponse {
    private BigDecimal priceTotal;
    private Phone phone;
    private Long quantity;

    public CartItemResponse() {
    }

    public CartItemResponse(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
