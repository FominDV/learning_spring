package ru.fomin.free_progect.services;

import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.ProductEn;
import ru.fomin.free_progect.entities.ProductPriceEn;

public interface ProductPriceService {

    OrderItem getOrderItemByProductId(Long productId);

    OrderItem getOrderItemByProductPriceId(Long productPriceId);

    ProductPriceEn getOrderItemEn(Long productPriceId);

    ProductPriceEn findByProductAndPrice(Long productId, Long cost);

    ProductPriceEn create(Long cost, ProductEn productEn);

}
