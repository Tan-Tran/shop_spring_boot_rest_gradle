package com.eximias.ecommerce.mapper;

import com.eximias.ecommerce.dto.CustomerDTO;
import com.eximias.ecommerce.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toDto(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
    @Mapping(target = "id", ignore = true)
    Customer toEntity(@MappingTarget  Customer customer, CustomerDTO customerDTO);
}
