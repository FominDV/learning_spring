package ru.fomin.free_progect.services;

import ru.fomin.free_progect.domains.OrderItem;

public interface ProductPriceService {

    OrderItem getOrderItem(Long productId);

}
