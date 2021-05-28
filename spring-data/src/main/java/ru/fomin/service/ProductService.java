package ru.fomin.service;

import ru.fomin.entity.ProductEn;
import ru.fomin.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductsByFilter();

}
