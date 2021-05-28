package ru.fomin.service;

import ru.fomin.domain.Product;
import ru.fomin.domain.ProductFilter;

import java.util.List;

public interface ProductService {

    List<Product> getProductsByFilter(ProductFilter productFilter);

}
