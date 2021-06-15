package ru.fomin.free_progect.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price extends AbstractPersistable<Long> {

    Long cost;

    @OneToMany(mappedBy = "price")
    List<ProductPrice> productPrice;

}
