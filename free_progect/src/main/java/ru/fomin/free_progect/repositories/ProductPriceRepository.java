package ru.fomin.free_progect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fomin.free_progect.entities.ProductPriceEn;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPriceEn, Long> {

    Optional<ProductPriceEn> findByProduct_Id(Long productId);

}
