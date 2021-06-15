package ru.fomin.free_progect.services;

import ru.fomin.free_progect.domains.ProductFilter;
import ru.fomin.free_progect.domains.ProductPage;

public interface ProductService {

    ProductPage getProductsByFilter(ProductFilter productFilter, int page);

}
