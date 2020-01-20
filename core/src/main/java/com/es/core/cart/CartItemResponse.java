package com.es.core.cart;

public class CartItemResponse {
    private Long priceTotal;

    public CartItemResponse() {
    }

    public CartItemResponse(Long priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Long getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Long priceTotal) {
        this.priceTotal = priceTotal;
    }
}
