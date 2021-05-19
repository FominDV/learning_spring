package ru.fomin.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    Long id;
    String title;
    Long cost;
    String description;
}
