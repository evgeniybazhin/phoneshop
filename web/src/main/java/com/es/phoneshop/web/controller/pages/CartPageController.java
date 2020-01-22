package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public void getCart() {
        cartService.getCart();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCart(@RequestParam(value = "quantity") Long quantity, @RequestParam(value = "id") Long id, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            Map<Long, Long> itemToUpdate = new HashMap<>();
            itemToUpdate.put(id, quantity);
            cartService.update(itemToUpdate);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void removeFromCart(@RequestParam(value = "id") Long id, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            cartService.remove(id);
        }
    }
}
