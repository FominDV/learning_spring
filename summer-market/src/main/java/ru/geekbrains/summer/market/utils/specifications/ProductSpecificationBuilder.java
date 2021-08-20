package ru.geekbrains.summer.market.utils.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.summer.market.model.Product;

import java.math.BigDecimal;

public interface ProductSpecificationBuilder {

    Specification<Product> build(BigDecimal minPrice, BigDecimal maxPrice, String title);

}
