package ru.fomin.productservice.mappers;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import ru.fomin.dto.ProductDto;
import ru.fomin.productservice.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Converter<ProductDto, Product> {

    @Override
    Product convert(ProductDto source);

}
