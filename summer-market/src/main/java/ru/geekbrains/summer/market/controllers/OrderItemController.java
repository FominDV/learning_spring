package ru.geekbrains.summer.market.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.summer.market.dto.OrderItemDto;
import ru.geekbrains.summer.market.model.OrderItem;
import ru.geekbrains.summer.market.services.OrderItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/items")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemController {

    final OrderItemService orderItemService;

    @GetMapping
    /**В зависимости от переданного параметра возвращает все детали заказа,
     * либо относящиеся лишь к определённому заказу.*/
    public List<OrderItemDto> getOrderItems(@RequestParam(name = "order",required = false) Long orderId) {
        List<OrderItem> orderItemList = orderId != null ?
                orderItemService.findByOrderId(orderId) :
                orderItemService.findAll();
        return orderItemList.stream()
                .map(OrderItemDto::of)
                .collect(Collectors.toList());
    }

}
