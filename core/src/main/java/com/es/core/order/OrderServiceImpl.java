package com.es.core.order;

import com.es.core.cart.Cart;
import com.es.core.cart.CartItem;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Value("${delivery.price}")
    BigDecimal deliveryPrice;
    @Override
    public Order createOrder(Cart cart) {
       Order order = new Order();
        List<OrderItem> orderItems = new ArrayList();
       for(CartItem cartItem : cart.getCartItems()){
           OrderItem orderItem = new OrderItem();
           orderItem.setPhone(cartItem.getPhone());
           orderItem.setQuantity(cartItem.getQuantity());
           orderItems.add(orderItem);
       }
       order.setOrderItems(orderItems);
       order.setDeliveryPrice(deliveryPrice);
       order.setSubtotal(cart.getTotalPrice());
       order.setTotalPrice(cart.getTotalPrice().add(deliveryPrice));
       return order;
    }

    @Override
    public void placeOrder(Order order) throws OutOfStockException {
        throw new UnsupportedOperationException("TODO");
    }
}
