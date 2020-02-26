package com.es.core.cart;

import java.math.BigDecimal;

public class CartItemResponse {
    private BigDecimal priceTotal;
    private Long quantity;
    private String message;

    public CartItemResponse() {
    }

    public CartItemResponse(BigDecimal priceTotal, String message) {
        this.priceTotal = priceTotal;
        this.message = message;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
