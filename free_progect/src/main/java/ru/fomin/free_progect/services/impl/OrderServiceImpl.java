package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.fomin.free_progect.beans.Cart;
import ru.fomin.free_progect.services.OrderService;

import javax.annotation.Resource;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    @Resource
    Cart cart;

    @Override
    public void createOrder() {
       // cart.getOrderItemList();
    }

}
