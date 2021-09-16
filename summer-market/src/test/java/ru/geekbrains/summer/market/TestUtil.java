package ru.geekbrains.summer.market;

import ru.geekbrains.summer.market.dto.OrderItemDto;

import java.math.BigDecimal;

public class TestUtil {

    public static OrderItemDto getOrderItemDto(Long productId, String productTitle, double productPrice, int quantity) {
        BigDecimal pricePerProduct = BigDecimal.valueOf(productPrice);
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(productId);
        orderItemDto.setProductTitle(productTitle);
        orderItemDto.setPricePerProduct(pricePerProduct);
        orderItemDto.setPrice(pricePerProduct.multiply(BigDecimal.valueOf(quantity)));
        orderItemDto.setQuantity(quantity);
        return orderItemDto;
    }

}
