package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.order.OrderService;
import com.es.core.order.OutOfStockException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {
    @Resource
    private OrderService orderService;
    @Resource
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getOrder(ModelAndView modelAndView) throws OutOfStockException {
        modelAndView.setViewName("order");
        modelAndView.addObject("order", orderService.createOrder(cartService.getCart()));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void placeOrder() throws OutOfStockException {
        orderService.placeOrder(null);
    }
}
