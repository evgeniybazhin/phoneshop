package com.es.phoneshop.web.controller.pages;

import javax.annotation.Resource;

import com.es.core.model.phone.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.es.core.model.phone.PhoneDao;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping (value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneDao phoneDao;

    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(@RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "sortBy", required = false) String sortBy,
                                  @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int total = phoneDao.getCount();

        int pagesTotal = total / 10 + 1;
        if (page > pagesTotal || page <= 0)
            throw new RuntimeException();
        int firstIndex = (page - 1) * 10;
        List<Phone> phoneList = phoneDao.findAll(firstIndex, 10, search, sortBy);
        model.addAttribute("currentPage", page);
        model.addAttribute("pagesTotal", pagesTotal);
        model.addAttribute("phones", phoneList);
        return "productList";
    }
}
