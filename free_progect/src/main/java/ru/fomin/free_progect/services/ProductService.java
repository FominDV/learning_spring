package ru.fomin.free_progect.services;

import ru.fomin.free_progect.models.ProductFilter;
import ru.fomin.free_progect.models.ProductPage;

public interface ProductService {

    ProductPage getProductsByFilter(ProductFilter productFilter, int page);

}
