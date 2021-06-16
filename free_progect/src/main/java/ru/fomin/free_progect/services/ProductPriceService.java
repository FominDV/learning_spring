package ru.fomin.free_progect.services;

import ru.fomin.free_progect.domains.OrderItem;

public interface ProductPriceService {

    OrderItem getOrderItemByProductId(Long productId);

    OrderItem getOrderItemByProductPriceId(Long productPriceId);

}
