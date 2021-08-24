package ru.geekbrains.summer.market.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.summer.market.model.Product;

@Mapper(componentModel = "spring")
public interface SoapMapper {

    SoapMapper INSTANCE = Mappers.getMapper(SoapMapper.class);

    @Mapping(target = "product", source = ".")
    objects.GetProductByIdResponse toGetProductResponse(Product product);

    @Mapping(target = "categoryTitle", source = "category.title")
    objects.Product toProduct(Product product);

}
