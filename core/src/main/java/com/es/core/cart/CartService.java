package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.quickOrder.QuickOrder;

import java.util.List;
import java.util.Map;

public interface CartService {
    Cart getCart();
    void addPhone(Long phoneId, Long quantity);
    List<Phone> addPhone(QuickOrder quickOrder);
    void update(CartItemDTOWrapper itemsForUpdate);
    void remove(Long phoneId);
    void clear();
}
