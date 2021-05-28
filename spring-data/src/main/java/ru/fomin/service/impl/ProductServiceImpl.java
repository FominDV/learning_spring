package ru.fomin.service.impl;

import org.springframework.stereotype.Service;
import ru.fomin.domain.ProductFilter;
import ru.fomin.entity.ProductEn;
import ru.fomin.domain.Product;
import ru.fomin.repository.ProductRepository;
import ru.fomin.service.ProductService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductRepository productRepository;

    @Override
    public List<Product> getProductsByFilter(ProductFilter productFilter) {
        List<ProductEn> productEnList = productRepository.findAll();
        return convertToProductList(productEnList);
    }

    private List<Product> convertToProductList(List<ProductEn> productEnList) {
        return productEnList.stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    private Product convertToProduct(ProductEn productEn) {
        Long price = productEn.getPrice();
        return Product.builder()
                .id(productEn.getId())
                .title(productEn.getTitle())
                .priceRub((int) (price / 100))
                .pricePenny((int) (price % 100))
                .build();
    }

}
