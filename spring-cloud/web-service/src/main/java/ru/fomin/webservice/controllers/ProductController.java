package ru.fomin.webservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fomin.dto.ProductDto;
import ru.fomin.webservice.clients.ProductClient;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    final ProductClient productClient;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productClient.getProducts();
    }

}
