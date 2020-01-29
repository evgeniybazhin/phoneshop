package com.es.core.cart;

import java.util.List;
import java.util.Map;

public interface CartService {

    List<CartItem> getCart();

    String addPhone(Long phoneId, Long quantity);

    void update(CartItemDTOWrapper itemsForUpdate);

    void remove(Long phoneId);
}
