package com.es.core.cart;

import java.util.List;
import java.util.Map;

public interface CartService {

    List<CartItemResponse> getCart();

    void addPhone(Long phoneId, Long quantity);

    void update(Map<Long, Long> items);

    void remove(Long phoneId);
}
