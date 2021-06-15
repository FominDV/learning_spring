package ru.fomin.free_progect.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseTimeEntity {

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @OneToOne(mappedBy = "product")
    ProductPrice productPrice;

}
