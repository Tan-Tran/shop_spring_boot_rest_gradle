package com.eximias.ecommerce.mapper;

import com.eximias.ecommerce.dto.OrdersDTO;
import com.eximias.ecommerce.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses={CustomerMapper.class}, componentModel = "spring")
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);
    @Mapping(source = "orders.customer", target = "customerDTO")
    OrdersDTO toDTO(Orders orders);

    @Mapping(source = "ordersDTO.customerDTO", target = "customer")
    Orders toEntity(OrdersDTO ordersDTO);
}
