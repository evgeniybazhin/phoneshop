package com.es.core.order;

import com.es.core.cart.Cart;
import com.es.core.cart.CartItem;
import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderDao;
import com.es.core.model.order.OrderItem;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.StockDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Value("${delivery.price}")
    BigDecimal deliveryPrice;
    @Resource
    private OrderDao orderDao;
    @Resource
    private StockDao stockDao;
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private CartService cartService;

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
    public Long placeOrder(Order order){
        List<OrderItem> itemsToRemove = new ArrayList<>();
        for(OrderItem item : order.getOrderItems()){
            Long phoneId = item.getPhone().getId();
            Stock stock = stockDao.getCountInStock(phoneId);
            if(stock.getStock() - stock.getReserved() < item.getQuantity()){
                itemsToRemove.add(item);
            }
        }
        if(!itemsToRemove.isEmpty()){
            order.getOrderItems().removeAll(itemsToRemove);
            repriceOrder(order);
        }
        cartService.clear();
        return orderDao.save(order);
    }

    private void repriceOrder(Order order){
        BigDecimal subtotalPrice = BigDecimal.ZERO;
        for(OrderItem item : order.getOrderItems()){
            Optional<Phone> optionalPhone = phoneDao.getById(item.getPhone().getId());
            Long quantity = item.getQuantity();
            BigDecimal price = optionalPhone.get().getPrice().multiply(BigDecimal.valueOf(quantity));
            subtotalPrice = subtotalPrice.add(price);
        }
        order.setSubtotal(subtotalPrice);
        order.setTotalPrice(subtotalPrice.add(deliveryPrice));
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        return orderDao.getById(id);
    }
}
