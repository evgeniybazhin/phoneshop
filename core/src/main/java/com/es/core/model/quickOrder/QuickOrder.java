package com.es.core.model.quickOrder;

import java.util.HashMap;
import java.util.Map;

public class QuickOrder {
    private Map<Long, Long> quickOrderItems = new HashMap<>();

    public QuickOrder() {
    }

    public Map<Long, Long> getQuickOrderItems() {
        return quickOrderItems;
    }

    public void setQuickOrderItems(Map<Long, Long> quickOrderItems) {
        this.quickOrderItems = quickOrderItems;
    }
}
