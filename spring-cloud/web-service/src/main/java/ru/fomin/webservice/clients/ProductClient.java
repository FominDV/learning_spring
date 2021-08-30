package ru.fomin.webservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fomin.dto.ProductDto;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDto> getProducts();

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id);

}
