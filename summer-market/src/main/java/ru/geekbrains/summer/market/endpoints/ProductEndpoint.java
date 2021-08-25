package ru.geekbrains.summer.market.endpoints;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.summer.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.summer.market.mappers.SoapMapper;
import ru.geekbrains.summer.market.model.Product;
import ru.geekbrains.summer.market.services.ProductService;
import ru.geekbrains.summer.market.soap.GetProductByIdRequest;
import ru.geekbrains.summer.market.soap.GetProductByIdResponse;
import ru.geekbrains.summer.market.soap.GetProductsRequest;
import ru.geekbrains.summer.market.soap.GetProductsResponse;

@Endpoint
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.summer-market.ru/spring/ws/products";

    final ProductService productService;
    final SoapMapper soapMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        return soapMapper.toGetProductsResponse(productService.findAll());
    }

    /*
    <SOAP-ENV:
        Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="http://www.summer-market.ru/spring/ws/products">
      <SOAP-ENV:Header/>
      <SOAP-ENV:Body>
        <f:getProductByIdRequest>
            <f:id>2</f:id>
        </f:getProductByIdRequest>
      </SOAP-ENV:Body>
    </SOAP-ENV:Envelope>
    */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        Long id = request.getId();
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return soapMapper.toGetProductResponse(product);
    }

}
