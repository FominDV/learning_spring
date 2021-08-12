package ru.geekbrains.summer.market.services;

import ru.geekbrains.summer.market.dto.OrderDto;
import ru.geekbrains.summer.market.model.User;

import java.util.List;

public interface OrderServiceToController {
    void createOrder(User user, String address, String phone);
    List<OrderDto> findAllOrderDto();
}
