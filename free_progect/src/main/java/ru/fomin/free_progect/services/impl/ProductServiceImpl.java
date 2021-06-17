package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fomin.free_progect.models.ProductFilter;
import ru.fomin.free_progect.models.ProductPage;
import ru.fomin.free_progect.entities.ProductEn;
import ru.fomin.free_progect.mappers.PageMapper;
import ru.fomin.free_progect.repositories.ProductRepository;
import ru.fomin.free_progect.services.ProductService;

import javax.annotation.Resource;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    @Value("${pageSize}")
    int pageSize;

    @Resource
    ProductRepository productRepository;

    @Resource
    PageMapper pageMapper;

    @Override
    public ProductPage getProductsByFilter(ProductFilter productFilter, int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductEn> productEnPage = productRepository.findAllByMinAndMaxPrice(
                productFilter.getMinPriceLong(),
                productFilter.getMaxPriceLong(),
                pageable
        );
        return pageMapper.convertToProductPage(productEnPage);
    }





}
