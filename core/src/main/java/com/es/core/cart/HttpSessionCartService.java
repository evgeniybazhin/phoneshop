package com.es.core.cart;

import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.Stock;
import com.es.core.model.phone.StockDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


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
        Phone phone = phoneDao.getById(phoneId);
        Stock stock = stockDao.getCountInStock(phoneId);
        if (stock.getStock() < quantity){

        }
        cart.addItem(phone, quantity);
    }

    @Override
    public void update(Map<Long, Long> items) {

    }

    @Override
    public void remove(Long phoneId) {

    }


}
