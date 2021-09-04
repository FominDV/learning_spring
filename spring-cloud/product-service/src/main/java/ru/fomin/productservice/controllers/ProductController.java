package ru.fomin.productservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;
import ru.fomin.dto.ProductDto;
import ru.fomin.productservice.entities.Product;
import ru.fomin.productservice.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET})
public class ProductController {

    final ProductService productService;
    final ConversionService conversionService;

    @GetMapping
    List<ProductDto> getProducts() {
        return productService.findAllProducts().stream()
                .map(product -> conversionService.convert(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.remove(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createOrUpdate(conversionService.convert(productDto, Product.class));
        return conversionService.convert(product, ProductDto.class);
    }

}
