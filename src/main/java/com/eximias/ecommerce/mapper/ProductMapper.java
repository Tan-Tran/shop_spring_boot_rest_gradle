package com.eximias.ecommerce.mapper;


import com.eximias.ecommerce.dto.ProductDTO;
import com.eximias.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
}
