package com.es.phoneshop.web.controller.pages;

import com.es.core.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {
    @Resource
    private OrderService orderService;

    @GetMapping
    public ModelAndView orderOverview(ModelAndView modelAndView, HttpSession httpSession){
        modelAndView.setViewName("orderOverview");
        modelAndView.addObject("order", orderService.getOrder((Long) httpSession.getAttribute("orderId")).get());
        return modelAndView;
    }
}
