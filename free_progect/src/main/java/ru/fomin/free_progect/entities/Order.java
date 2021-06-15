package ru.fomin.free_progect.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseTimeEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "order")
    List<OrderItem> items;

    Long totalPrice;

    public Order(User user, List<OrderItem> items) {
        this.user = user;
        this.items = items;
    }
}
