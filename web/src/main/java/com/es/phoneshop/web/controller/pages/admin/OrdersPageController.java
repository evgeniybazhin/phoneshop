package com.es.phoneshop.web.controller.pages.admin;

import com.es.core.model.order.Order;
import com.es.core.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrdersPageController {
    @Resource
    private OrderService orderService;

    @GetMapping
    public ModelAndView showOrders(ModelAndView modelAndView){
        modelAndView.setViewName("orders");
        modelAndView.addObject("orders", orderService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView showOrderDetails(@PathVariable("id") Long id, ModelAndView modelAndView){
        modelAndView.setViewName("orderDetail");
        modelAndView.addObject("order", orderService.getOrder(id).get());
        return modelAndView;
    }

    @PostMapping(path = "/{id}/updateStatus", params = "status")
    public String updateStatus(@PathVariable Long id, @RequestParam("status") String statusValue){
        Optional<Order> order = orderService.getOrder(id);
        if(order.isPresent() && !statusValue.isEmpty()){
            orderService.updateStatus(statusValue, id);
        }
        return "redirect:/admin/orders/" + id;
    }
}
