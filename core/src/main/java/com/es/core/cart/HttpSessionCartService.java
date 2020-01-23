package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.StockDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@Service
public class HttpSessionCartService implements CartService {
    @Resource
    private PhoneDao phoneDao;
    @Resource
    private StockDao stockDao;
    @Resource
    private Cart cart;
    @Override
    public List<CartItemResponse> getCart() {
        List<CartItemResponse> cartItems = new ArrayList<>();
        for(CartItem cartItem : cart.getCartItems()){
            CartItemResponse cartItemResponse = new CartItemResponse();
            cartItemResponse.setPhone(phoneDao.getById(cartItem.getPhoneId()).get());
            cartItemResponse.setQuantity(cartItem.getQuantity());
            cartItems.add(cartItemResponse);
        }
        return cartItems;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        if(!isAddedPhone(phoneId)){
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
    }
    private boolean isAddedPhone(Long phoneId){
        for(CartItem cartItem : cart.getCartItems()){
            if(cartItem.getPhoneId().equals(phoneId)){
                return true;
            }
        }
        return false;
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
                cartItem.setQuantity(items.get(cartItem.getPhoneId()));
            }
        }
        repriceOrder();
    }

    @Override
    public void remove(Long phoneId) {
        List<CartItem> itemsForIter = cart.getCartItems();
        for(CartItem item : itemsForIter){
            if(item.getPhoneId().equals(phoneId)){
                cart.getCartItems().remove(item);
            }
        }

        repriceOrder();
    }

    private void repriceOrder(){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(CartItem cartItem : cart.getCartItems()){
            Optional<Phone> optionalPhone = phoneDao.getById(cartItem.getPhoneId());
            Long quantity = cartItem.getQuantity();
            BigDecimal price = optionalPhone.get().getPrice().multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(price);
        }
        cart.setTotalPrice(totalPrice);
    }
}
