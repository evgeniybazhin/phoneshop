package com.es.core.cart;

import java.util.HashMap;
import java.util.Map;

public class CartItemDTOWrapper {
    private Map<Long, Long> itemsForUpdate = new HashMap<>();

    public CartItemDTOWrapper() {
    }

    public CartItemDTOWrapper(Map<Long, Long> itemsForUpdate) {
        this.itemsForUpdate = itemsForUpdate;
    }

    public Map<Long, Long> getItemsForUpdate() {
        return itemsForUpdate;
    }

    public void setItemsForUpdate(Map<Long, Long> itemsForUpdate) {
        this.itemsForUpdate = itemsForUpdate;
    }
}
