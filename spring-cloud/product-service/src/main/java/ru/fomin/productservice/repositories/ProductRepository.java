package ru.fomin.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fomin.productservice.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
