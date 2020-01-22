package com.es.core.cart;

import java.math.BigDecimal;

public class CartItemResponse {
    private BigDecimal priceTotal;

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
}
