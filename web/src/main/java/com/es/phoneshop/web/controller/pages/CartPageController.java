package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.Cart;
import com.es.core.cart.CartItemDTOWrapper;
import com.es.core.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCart(ModelAndView modelAndView) {
        modelAndView.setViewName("cart");
        modelAndView.addObject("cart", cartService.getCart());
        modelAndView.addObject("updateForm", new CartItemDTOWrapper());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView updateCart(@ModelAttribute(name = "updateForm") @Valid CartItemDTOWrapper itemsForUpdate, BindingResult bindingResult, ModelAndView modelAndView) {
        if(!bindingResult.hasErrors()){
            cartService.update(itemsForUpdate);
            modelAndView.setViewName("redirect:/cart");
            return modelAndView;
        }
        modelAndView.setViewName("cart");
        modelAndView.addObject("cart", cartService.getCart());
        return modelAndView;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ModelAndView removeFromCart(@PathVariable("id") Long id, ModelAndView modelAndView){
        modelAndView.setViewName("redirect:/cart");
        cartService.remove(id);
        return modelAndView;
    }
}
