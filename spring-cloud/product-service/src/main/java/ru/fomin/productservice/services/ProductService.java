package ru.fomin.productservice.services;

import ru.fomin.productservice.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    void remove(Long id);

    Product createOrUpdate(Product product);

}
