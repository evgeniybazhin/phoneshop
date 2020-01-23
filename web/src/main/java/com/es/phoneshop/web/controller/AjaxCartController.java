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
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CartItemResponse addPhone(@RequestBody @Valid CartItem cartItem, BindingResult bindingResult){
        CartItemResponse cartItemResponse = new CartItemResponse();
        if(!bindingResult.hasErrors()){
            cartService.addPhone(cartItem.getPhoneId(), cartItem.getQuantity());
            return cartItemResponse;
        }
        return null;
    }
}
