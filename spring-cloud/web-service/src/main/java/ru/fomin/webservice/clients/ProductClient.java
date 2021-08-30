package ru.fomin.webservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.fomin.dto.ProductDto;

import java.util.List;

@FeignClient("product-service")
@RequestMapping("/products")
public interface ProductClient {

    @GetMapping
    List<ProductDto> getProducts();

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id);

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto);

}
