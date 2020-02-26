package model.phone;

import com.es.core.cart.Cart;
import com.es.core.cart.HttpSessionCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class HttpSessionCartServiceTest {
    @Resource
    private Cart cart;
    @Resource
    private HttpSessionCartService httpSessionCartService;

    @Test
    public void addPhoneTest(){
        Long phoneId = new Long(1003);
        Long quantity = new Long(10);
        httpSessionCartService.addPhone(phoneId, quantity);
        assertTrue(cart.getCartItems() != null);
    }

    @Test
    public void addPhoneWithErrorTest(){
        Long phoneId = new Long(1003);
        Long quantity = new Long(300);
        httpSessionCartService.addPhone(phoneId, quantity);
        assertTrue(cart.getCartItems().size() == 0);
    }

    @Test
    public void removeItemTest(){
        Long phoneId = new Long(1003);
        Long quantity = new Long(5);
        httpSessionCartService.addPhone(phoneId, quantity);
        httpSessionCartService.remove(phoneId);
        assertNull(cart.getCartItems());
    }
}
