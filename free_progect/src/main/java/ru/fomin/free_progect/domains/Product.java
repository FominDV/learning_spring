package ru.fomin.free_progect.domains;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    Long id;
    String title;
    int priceRub;
    int pricePenny;
}
