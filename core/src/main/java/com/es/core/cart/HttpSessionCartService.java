package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.StockDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class HttpSessionCartService implements CartService {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private StockDao stockDao;
    @Resource
    private Cart cart;
    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        try {
            Optional<Phone> optionalPhone = phoneDao.getById(phoneId);
            Stock stock = stockDao.getCountInStock(phoneId);
            if(quantity > stock.getStock()){
                throw new RuntimeException();
            }
            CartItem cartItem = new CartItem(phoneId, quantity);
            cart.getCartItems().add(cartItem);
            setPrice(optionalPhone, quantity);
            stockDao.updateStock(phoneId, quantity);
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
    }

    private void setPrice(Optional<Phone> phone, Long quantity){
        BigDecimal totalPrice = cart.getTotalPrice();
        BigDecimal price;
        price = phone.get().getPrice().multiply(BigDecimal.valueOf(quantity));
        totalPrice = totalPrice.add(price);
        cart.setTotalPrice(totalPrice);
    }

    @Override
    public void update(Map<Long, Long> items) {
        List<CartItem> itemList = cart.getCartItems();
        for(CartItem cartItem : itemList){
            if(items.containsKey(cartItem.getPhoneId())){
                repriceCartAfterUpdate(items, cartItem);
            }
        }
    }

    private void repriceCartAfterUpdate(Map<Long, Long> items, CartItem cartItem){
        Optional<Phone> phone = phoneDao.getById(cartItem.getPhoneId());
        BigDecimal price;
        BigDecimal totalPrice = subtractPrice(cartItem.getPhoneId(), cartItem);
        price = phone.get().getPrice().multiply(BigDecimal.valueOf(items.get(cartItem.getPhoneId())));
        totalPrice = totalPrice.add(price);
        cart.setTotalPrice(totalPrice);
        stockDao.updateStock(cartItem.getPhoneId(), items.get(cartItem.getPhoneId()));
    }

    @Override
    public void remove(Long phoneId) {
        CartItem cartItem = null;
        for(CartItem item : cart.getCartItems()){
            if(item.getPhoneId() == phoneId){
                cartItem = item;
            }
        }
        cart.getCartItems().remove(phoneId);
        repriceCartAfterRemove(cartItem, phoneId);
    }

    private void repriceCartAfterRemove(CartItem cartItem, Long phoneId){
        BigDecimal totalPrice = subtractPrice(phoneId, cartItem);
        cart.setTotalPrice(totalPrice);
        stockDao.updateStock(phoneId, null);
    }

    private BigDecimal subtractPrice(Long phoneId, CartItem cartItem){
        Optional<Phone> phone = phoneDao.getById(phoneId);
        BigDecimal totalPrice = cart.getTotalPrice();
        BigDecimal price;
        price = phone.get().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        totalPrice = totalPrice.subtract(price);
        return totalPrice;
    }


}
