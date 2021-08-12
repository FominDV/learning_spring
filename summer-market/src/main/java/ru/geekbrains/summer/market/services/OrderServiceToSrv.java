package ru.geekbrains.summer.market.services;

import ru.geekbrains.summer.market.model.Order;

import java.util.List;

public interface OrderServiceToSrv {
    List<Order> findAll();
}
