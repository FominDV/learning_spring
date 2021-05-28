package ru.fomin.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilter {
    Double minPrice = 0.0;
    Double maxPrice = 9999.99;
}
