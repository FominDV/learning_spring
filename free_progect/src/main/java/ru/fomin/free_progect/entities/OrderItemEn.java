package ru.fomin.free_progect.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "order_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemEn extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "product_price_id", nullable = false)
    ProductPriceEn productPrice;

    @Column(name = "quantity")
    int quantity;


    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEn order;

    public OrderItemEn(ProductEn product) {
        quantity = 1;
        productPrice = product.getProductPrice();
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }
}
