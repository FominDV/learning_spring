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
    @JoinColumn(name = "product_id")
    ProductEn product;

    @Column(name = "quantity")
    int quantity;

    @ManyToOne
    @JoinColumn(name = "price_id")
    PriceEn price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEn order;

    public OrderItemEn(ProductEn product) {
        this.product = product;
        quantity = 1;
        price = product.getProductPrice().getPrice();
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }
}
