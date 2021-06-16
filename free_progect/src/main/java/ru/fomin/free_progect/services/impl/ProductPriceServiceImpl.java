package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.ProductPriceEn;
import ru.fomin.free_progect.mappers.OrderItemMapper;
import ru.fomin.free_progect.repositories.ProductPriceRepository;
import ru.fomin.free_progect.services.ProductPriceService;

import javax.annotation.Resource;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPriceServiceImpl implements ProductPriceService {

    @Resource
    ProductPriceRepository productPriceRepository;

    @Resource
    OrderItemMapper orderItemMapper;

    @Override
    public OrderItem getOrderItemByProductId(Long productId) {
        ProductPriceEn productPriceEn = productPriceRepository.findByProduct_Id(productId)
                .orElseThrow(() -> new RuntimeException("product was not found"));
        return orderItemMapper.convertToOrderItem(productPriceEn);
    }

    @Override
    public OrderItem getOrderItemByProductPriceId(Long productPriceId) {
        ProductPriceEn productPriceEn = productPriceRepository.findById(productPriceId)
                .orElseThrow(() -> new RuntimeException("product was not found"));
        return orderItemMapper.convertToOrderItem(productPriceEn);
    }


}
