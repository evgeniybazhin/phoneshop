package com.es.core.model.phone;

public interface StockDao {
    Stock getCountInStock(Long key);
    void updateStock(Long phoneId, Long quantity);
}
