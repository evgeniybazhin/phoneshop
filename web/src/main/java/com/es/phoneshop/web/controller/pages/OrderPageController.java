package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderDTO;
import com.es.core.order.OrderService;
import com.es.core.order.OutOfStockException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        modelAndView.addObject("info", new OrderDTO());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView placeOrder(@ModelAttribute(name = "info") @Valid OrderDTO orderDTO, BindingResult bindingResult, ModelAndView modelAndView, HttpSession httpSession) throws OutOfStockException {
        modelAndView.setViewName("order");
        if(!bindingResult.hasErrors()){
            Order order = orderService.createOrder(cartService.getCart());
            if(order != null){
                orderService.setPersonalInfo(order, orderDTO);
                httpSession.setAttribute("orderId", orderService.placeOrder(order));
                modelAndView.setViewName("redirect:/orderOverview");
                return modelAndView;
            }
        }
        modelAndView.addObject("order", orderService.createOrder(cartService.getCart()));
        return modelAndView;
    }
}
