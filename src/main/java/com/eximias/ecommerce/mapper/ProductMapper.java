package com.eximias.ecommerce.mapper;


import com.eximias.ecommerce.dto.ProductDTO;
import com.eximias.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
    @Mapping(target = "id", ignore = true)
    Product toEntity(@MappingTarget Product product, ProductDTO productDTO);
}
