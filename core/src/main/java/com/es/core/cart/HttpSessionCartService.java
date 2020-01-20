package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.StockDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        Optional<Phone> optionalPhone = phoneDao.getById(phoneId);
        Stock stock = stockDao.getCountInStock(phoneId);
        if(quantity < stock.getStock()){
            throw new RuntimeException();
        }
        CartItem cartItem = new CartItem(phoneId, quantity);
        cart.getCartItems().add(cartItem);
        setPrice(optionalPhone, quantity);
    }

    private void setPrice(Optional<Phone> phone, Long quantity){
        Long price = cart.getTotalPrice().longValue();
        price += phone.get().getPrice().longValue() * quantity;
        cart.setTotalPrice(price);
    }

    @Override
    public void update(Map<Long, Long> items) {

    }

    @Override
    public void remove(Long phoneId) {

    }


}
