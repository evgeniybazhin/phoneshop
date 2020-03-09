package com.es.core.order;

import com.es.core.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Cart cart);
    Long placeOrder(Order order) throws OutOfStockException;
    Optional<Order> getOrder(Long id);
    void setPersonalInfo(Order order, OrderDTO orderDTO);
    List<Order> findAll();
    void updateStatus(String statusValue, Long id);
}
