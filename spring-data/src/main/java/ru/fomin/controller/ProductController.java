package ru.fomin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.fomin.model.Product;
import ru.fomin.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getProductList(Model model) {
        List<Product> productEnList = productService.getProductsByFilter();
        model.addAttribute("products", productEnList);
        return "products";
    }

}
