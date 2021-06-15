package ru.fomin.free_progect.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products_prices")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPrice extends BaseTimeEntity {

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "price_id")
    Price price;

}
