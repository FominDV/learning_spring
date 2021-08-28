package ru.fomin.productservice.mappers;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import ru.fomin.dto.ProductDto;
import ru.fomin.productservice.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper extends Converter<Product, ProductDto> {

    @Override
    ProductDto convert(Product source);

}
