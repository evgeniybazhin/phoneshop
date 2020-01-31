package com.es.phoneshop.web.controller;

import com.es.core.cart.Cart;
import com.es.core.cart.CartItemDTO;
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
    @Resource
    private Cart cart;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CartItemResponse addPhone(@RequestBody @Valid CartItemDTO cartItem, BindingResult bindingResult){
        CartItemResponse cartItemResponse;
        if(!bindingResult.hasErrors()){
            cartService.addPhone(cartItem.getPhoneId(), cartItem.getQuantity());
            cartItemResponse = new CartItemResponse(cart.getTotalPrice());
            return cartItemResponse;
        }
        return null;
    }
}
