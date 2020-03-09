package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.quickOrder.QuickOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/quickOrder")
public class QuickOrderController {
    @Resource
    private CartService cartService;
    @Resource
    private PhoneDao phoneDao;

    @GetMapping
    public ModelAndView getPage(ModelAndView modelAndView){
        modelAndView.setViewName("quickOrder");
        modelAndView.addObject("cart", cartService.getCart());
        modelAndView.addObject("itemsQuickOrder", phoneDao.findAll(0, 10, null, null));
        modelAndView.addObject("quickOrder", new QuickOrder());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addToCart(@ModelAttribute("quickOrder")QuickOrder quickOrder, ModelAndView modelAndView, HttpSession httpSession){
        modelAndView.setViewName("quickOrder");
        if(quickOrder != null){
            List<Phone> returnItems = cartService.addPhone(quickOrder);
            httpSession.setAttribute("currentItems", returnItems);
            modelAndView.addObject("cartInfo", cartService.getCart());
            return modelAndView;
        }
        return modelAndView;
    }
}
