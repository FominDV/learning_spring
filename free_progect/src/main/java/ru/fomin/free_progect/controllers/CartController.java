package ru.fomin.free_progect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fomin.free_progect.domains.ProductFilter;
import ru.fomin.free_progect.services.CartService;
import ru.fomin.free_progect.util.UrlMaker;

import javax.annotation.Resource;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    CartService cartService;

    @GetMapping
    public String getCart(Model model) {

        return "cart";
    }

    @GetMapping("/{id}")
    public String addProduct(@PathVariable(name = "id") Long productId, @ModelAttribute ProductFilter productFilter, @RequestParam Integer page) {
        cartService.addProduct(productId);
        return UrlMaker.getProductUrl(productFilter, page);
    }

}
