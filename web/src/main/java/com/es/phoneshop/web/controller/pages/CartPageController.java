package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.Cart;
import com.es.core.cart.CartItem;
import com.es.core.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartService cartService;
    @Resource
    private Cart cart;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCart(ModelAndView modelAndView) {
        modelAndView.setViewName("cart");
        modelAndView.addObject("cartList", cartService.getCart());
        modelAndView.addObject("priceTotal", cart.getTotalPrice());
        return modelAndView;
    }

    @PutMapping
    public void updateCart(@RequestBody CartItem cartItem, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            Map<Long, Long> itemToUpdate = new HashMap<>();
            itemToUpdate.put(cartItem.getPhoneId(), cartItem.getQuantity());
            cartService.update(itemToUpdate);
        }
    }

    @PostMapping
    public ModelAndView removeFromCart(@RequestParam(name = "id") Long id, ModelAndView modelAndView, BindingResult bindingResult){
        modelAndView.setViewName("redirect:/cart");
        if(!bindingResult.hasErrors()){
            cartService.remove(id);
        }
        return modelAndView;
    }
}
