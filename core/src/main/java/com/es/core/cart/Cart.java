package com.es.core.cart;

import com.es.core.model.phone.Phone;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public CartStatus getStatus() {
        long phonesTotal = items.stream()
                .reduce(0L, (num, item) -> num + item.getQuantity(), (num1, num2) -> num1 + num2);
        BigDecimal costTotal = items.stream()
                .reduce(BigDecimal.ZERO, (cost, item) -> cost.add(item.getPhone().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))), BigDecimal::add);
        return new CartStatus(phonesTotal, costTotal);
    }

    public void addItem(Phone phone, Long quantity) {
        Optional<CartItem> item = items.stream()
                .filter(cartItem -> cartItem.getPhone().getId().equals(phone.getId()))
                .findFirst();
        if (item.isPresent())
            item.get().setQuantity(item.get().getQuantity() + quantity);
        else
            items.add(new CartItem(phone, quantity));
    }
}