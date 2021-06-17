package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.PriceEn;
import ru.fomin.free_progect.entities.ProductEn;
import ru.fomin.free_progect.entities.ProductPriceEn;
import ru.fomin.free_progect.mappers.OrderItemMapper;
import ru.fomin.free_progect.repositories.ProductPriceRepository;
import ru.fomin.free_progect.services.ProductPriceService;

import javax.annotation.Resource;
import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("product price was not found"));
        return orderItemMapper.convertToOrderItem(productPriceEn);
    }

    @Override
    public ProductPriceEn getOrderItemEn(Long productPriceId) {
        return productPriceRepository.findById(productPriceId)
                .orElseThrow(() -> new RuntimeException("product price was not found"));
    }

    @Override
    public ProductPriceEn findByProductAndPrice(Long productId, Long cost) {
        return productPriceRepository.findByProduct_IdAndPrice_Cost(productId, cost)
                .orElseThrow(() -> new RuntimeException("product price was not found"));
    }

    @Override
    public ProductPriceEn create(Long cost, ProductEn productEn) {
        PriceEn priceEn = PriceEn.builder()
                .cost(cost)
                .build();
        //TODO
        return null;
    }


}
