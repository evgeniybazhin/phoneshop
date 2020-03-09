package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.StockDao;
import com.es.core.model.quickOrder.QuickOrder;
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

    @Override
    public List<Phone> addPhone(QuickOrder quickOrder) {
        Map<Long, Long> itemsToRemove = new HashMap<>();
        List<Phone> returnItems = new ArrayList<>();
        Map<Long, Long> quickOrderItems = quickOrder.getQuickOrderItems();
        for(Map.Entry<Long, Long> item : quickOrderItems.entrySet()){
            Long id = item.getKey();
            Long quantity = item.getValue();
            Optional<Phone> phone = phoneDao.getById(id);
            if(quantity == null || quantity <= 0 || stockDao.getCountInStock(id).getStock() < quantity){
                returnItems.add(phone.get());
                continue;
            }
            if(phone.isPresent()){
                CartItem cartItem = new CartItem(phone.get(), quantity);
                cart.getCartItems().add(cartItem);
                itemsToRemove.put(id, quantity);
            }
        }
        if(!itemsToRemove.isEmpty()){
            for(Map.Entry<Long, Long> item : itemsToRemove.entrySet()){
                if(quickOrderItems.containsKey(item.getKey())){
                    quickOrderItems.remove(item.getKey());
                }
            }
        }
        repriceOrder();
        return returnItems;
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
                Integer totalQuantity = stockDao.getCountInStock(phoneId).getStock();
                Long newQuantity = itemsForUpdate.getItemsForUpdate().get(phoneId);
                if(totalQuantity < newQuantity){
                    return;
                }
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
            Long quantity = cartItem.getQuantity();
            BigDecimal price = cartItem.getPhone().getPrice().multiply(BigDecimal.valueOf(quantity));
            totalPrice = totalPrice.add(price);
        }
        cart.setTotalPrice(totalPrice);
    }

}
