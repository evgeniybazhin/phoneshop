package com.es.phoneshop.web.controller;

import com.es.core.cart.CartService;
import com.es.core.cart.CartStatus;
import com.es.phoneshop.web.controller.throwable.IncorrectFormFormatException;
import com.es.phoneshop.web.controller.util.CartItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CartStatus> addPhone(@RequestBody @Valid CartItem cartItem, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            cartService.addPhone(cartItem.getPhoneId(), cartItem.getQuantity());
            return new ResponseEntity<>(cartService.getCart().getStatus(), HttpStatus.OK);
        }
        throw new IncorrectFormFormatException();
    }

    @ExceptionHandler(IncorrectFormFormatException.class)
    private @ResponseBody ResponseEntity<String> handleIncorrectFormFormat() {
        return new ResponseEntity<>("Incorrect format", HttpStatus.BAD_REQUEST);
    }
}
