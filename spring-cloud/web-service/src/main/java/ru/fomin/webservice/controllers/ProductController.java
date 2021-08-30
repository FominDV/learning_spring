package ru.fomin.webservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productClient.deleteProduct(id);
    }

    @PostMapping
    public ProductDto createProduct(@ModelAttribute ProductDto productDto) {
        return productClient.createProduct(productDto);
    }

}
