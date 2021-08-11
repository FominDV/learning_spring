package ru.geekbrains.summer.market.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.geekbrains.summer.market.model.OrderItem;
import ru.geekbrains.summer.market.repositories.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemService {

    final OrderItemRepository orderItemRepository;

    public List<OrderItem> findByOrderId(Long orderId){
        return orderItemRepository.findAllByOrder_Id(orderId);
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

}
