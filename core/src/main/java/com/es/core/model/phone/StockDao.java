package com.es.core.model.phone;

public interface StockDao {
    Stock getCountInStock(Long key);
    void addPhoneInStock(Long key, Integer stock, Integer reserved);
}
