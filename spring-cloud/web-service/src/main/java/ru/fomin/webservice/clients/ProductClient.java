package ru.fomin.webservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.fomin.dto.ProductDto;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDto> getProducts()

}
