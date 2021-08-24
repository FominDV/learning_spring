package ru.geekbrains.summer.market.endpoints;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.summer.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.summer.market.mappers.SoapMapper;
import ru.geekbrains.summer.market.model.Product;
import ru.geekbrains.summer.market.services.ProductService;

@Endpoint
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.summer-market.ru/spring/ws/products";

    final ProductService productService;

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
//    @ResponsePayload
//    public objects.GetProductsResponse getProducts(@RequestPayload objects.GetProductsRequest request) {
//
//    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public objects.GetProductByIdResponse getProducts(@RequestPayload objects.GetProductByIdRequest request) {
        Long id = request.getId();
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return SoapMapper.INSTANCE.toGetProductResponse(product);
    }

}
