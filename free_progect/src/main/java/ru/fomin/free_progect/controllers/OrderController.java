package ru.fomin.free_progect.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fomin.free_progect.services.OrderService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {

    @Resource
    OrderService orderService;

    @GetMapping("/create")
    public String createOrder() {
        orderService.createOrder();
        return "cart";
    }

}
