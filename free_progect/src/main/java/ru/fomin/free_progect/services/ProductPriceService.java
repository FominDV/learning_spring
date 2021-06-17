package ru.fomin.free_progect.services;

import ru.fomin.free_progect.models.OrderItem;
import ru.fomin.free_progect.entities.ProductPriceEn;

public interface ProductPriceService {

    OrderItem getOrderItemByProductId(Long productId);

    OrderItem getOrderItemByProductPriceId(Long productPriceId);

    ProductPriceEn getOrderItemEn(Long productPriceId);

}
