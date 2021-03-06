package com.es.core.model.order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> getById(Long id);
    Long save(Order order);
    List<Order> findAll();
    void updateStatus(OrderStatus orderStatus, Long id);
}
