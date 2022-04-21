package com.eximias.ecommerce.mapper;

import com.eximias.ecommerce.dto.CustomerDTO;
import com.eximias.ecommerce.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toDto(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}
