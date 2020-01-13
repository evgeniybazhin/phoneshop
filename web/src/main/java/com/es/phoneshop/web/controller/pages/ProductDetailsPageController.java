package com.es.phoneshop.web.controller.pages;

import com.es.core.model.phone.Phone;
import com.es.core.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {
    @Resource
    private PhoneService phoneService;

    @GetMapping
    public ModelAndView showDetailPhone(@RequestParam(value = "id", required = false) Long id, ModelAndView modelAndView){
        modelAndView.setViewName("productDetailPage");
        if (id != null){
            Optional<Phone> optionalPhone = Optional.ofNullable(phoneService.getById(id));
            optionalPhone.ifPresent(phone -> modelAndView.addObject("phone", optionalPhone.get()));
        }
        return modelAndView;
    }
}
