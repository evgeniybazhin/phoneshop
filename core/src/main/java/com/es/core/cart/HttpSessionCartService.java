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
    public Cart getCart() {
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Long quantity) {
        if(!isAddedPhone(phoneId)){
            Optional<Phone> optionalPhone = phoneDao.getById(phoneId);
            Stock stock = stockDao.getCountInStock(phoneId);
            if(quantity > stock.getStock()){
                return;
            }
            CartItem cartItem = new CartItem(optionalPhone.get(), quantity);
            cart.getCartItems().add(cartItem);
        }else{
            updateQuantity(phoneId, quantity);
        }
        repriceOrder();
    }

    private boolean isAddedPhone(Long phoneId){
        for(CartItem cartItem : cart.getCartItems()){
            if(cartItem.getPhone().getId().equals(phoneId)){
                return true;
            }
        }
        return false;
    }

    private void updateQuantity(Long phoneId, Long quantity){
        for(CartItem cartItem : cart.getCartItems()){
            if(cartItem.getPhone().getId().equals(phoneId)){
                Long newQuantity = cartItem.getQuantity() + quantity;
                cartItem.setQuantity(newQuantity);
            }
        }
    }

    @Override
    public void update(CartItemDTOWrapper itemsForUpdate) {
        List<CartItem> itemList = cart.getCartItems();
        for(CartItem cartItem : itemList){
            Long phoneId = cartItem.getPhone().getId();
            if(itemsForUpdate.getItemsForUpdate().containsKey(phoneId)){
                cartItem.setQuantity(itemsForUpdate.getItemsForUpdate().get(phoneId));
            }
        }
        repriceOrder();
    }

    @Override
    public void remove(Long phoneId) {
        List<CartItem> itemsToRemove = new ArrayList<>();
        for(CartItem item : cart.getCartItems()){
            if(item.getPhone().getId().equals(phoneId)){
                itemsToRemove.add(item);
            }
        }
        if(itemsToRemove.size() > 0){
            cart.getCartItems().removeAll(itemsToRemove);
        }
        repriceOrder();
    }

    @Override
    public void clear() {
        cart.setCartItems(new ArrayList<>());
        repriceOrder();
    }

    private void repriceOrder(){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(CartItem cartItem : cart.getCartItems()){
            Optional<Phone> optionalPhone = phoneDao.getById(cartItem.getPhone().getId());
            Long quantity = cartItem.getQuantity();
            BigDecimal price = optionalPhone.get().getPrice().multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(price);
        }
        cart.setTotalPrice(totalPrice);
    }

}
