package ru.geekbrains.summer.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.summer.market.model.OrderItem;
import ru.geekbrains.summer.market.model.Product;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private int quantity;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
        this.productTitle = product.getTitle();
    }

    public static OrderItemDto of(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto(orderItem.getProduct());
        orderItemDto.changeQuantity(orderItem.getQuantity() - 1);
        return orderItemDto;
    }

    public void changeQuantity(int amount) {
        quantity += amount;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
