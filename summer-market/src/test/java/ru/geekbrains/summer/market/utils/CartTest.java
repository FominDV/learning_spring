package ru.geekbrains.summer.market.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.summer.market.dto.OrderItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Cart.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CartTest {

    @Autowired
    Cart cart;

    List<OrderItemDto> orderItemDtoList;
    BigDecimal price;

    @BeforeAll
    public void init() {
        orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(getOrderItemDto(1L, "Milk", 34.5, 3));
        orderItemDtoList.add(getOrderItemDto(2L, "Potato", 65.99, 5));
        price = calculateTotalPrice();
    }

    @BeforeEach
    public void prepare() {
        cart.setItems(orderItemDtoList);
        cart.setPrice(price);
    }

    @Test
    public void clearTest() {
        cart.clear();
        assertEquals(Collections.emptyList(), cart.getItems());
        assertEquals(BigDecimal.ZERO, cart.getPrice());
    }

    private OrderItemDto getOrderItemDto(Long productId, String productTitle, double productPrice, int quantity) {
        BigDecimal pricePerProduct = BigDecimal.valueOf(productPrice);
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(productId);
        orderItemDto.setProductTitle(productTitle);
        orderItemDto.setPricePerProduct(pricePerProduct);
        orderItemDto.setPrice(pricePerProduct.multiply(BigDecimal.valueOf(quantity)));
        orderItemDto.setQuantity(quantity);
        return orderItemDto;
    }

    private BigDecimal calculateTotalPrice() {
        return orderItemDtoList.stream()
                .map(OrderItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}