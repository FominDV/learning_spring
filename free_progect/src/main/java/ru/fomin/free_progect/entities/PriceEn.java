package ru.fomin.free_progect.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceEn extends AbstractPersistable<Long> {

    Long cost;

    @OneToMany(mappedBy = "price")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<ProductPriceEn> productPrice;

}
