package ru.fomin.free_progect.mappers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.ProductPriceEn;

import javax.annotation.Resource;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemMapper {

    @Resource
    ProductMapper productMapper;

    public OrderItem convertToOrderItem(ProductPriceEn productPriceEn) {
        return OrderItem.builder()
                .product(productMapper.convertToProduct(productPriceEn.getProduct()))
                .productPriceId(productPriceEn.getId())
                .build();
    }

}
