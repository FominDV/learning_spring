package ru.fomin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fomin.domain.Product;
import ru.fomin.domain.ProductFilter;
import ru.fomin.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String getStartPosition(){
        return "redirect:/0";
    }

    @GetMapping("/{page}")
    public String getProductList(Model model,
                                 @PathVariable Integer page,
                                 @ModelAttribute ProductFilter productFilter) {
        List<Product> productEnList = productService.getProductsByFilter(productFilter);
        model.addAttribute("products", productEnList);
        model.addAttribute("filter", productFilter);
        model.addAttribute("page",page);
        model.addAttribute("pageCount", 10);
        return "products";
    }

}
