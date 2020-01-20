package com.es.phoneshop.web.controller;

import com.es.core.cart.CartItem;
import com.es.core.cart.CartItemResponse;
import com.es.core.cart.CartService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    private static final String WRONG_DATA = "wrong data";
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CartItemResponse addPhone(@RequestBody @Valid CartItem cartItem, BindingResult bindingResult){
        CartItemResponse cartItemResponse = new CartItemResponse();
        if(!bindingResult.hasErrors()){
            cartService.addPhone(cartItem.getPhoneId(), cartItem.getQuantity());
            cartItemResponse.setPriceTotal(cartService.getCart().getTotalPrice());
            return cartItemResponse;
        }
        return null;
    }
}
