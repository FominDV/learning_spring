package ru.fomin.productservice.services;

import ru.fomin.productservice.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

}
