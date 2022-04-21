package com.eximias.ecommerce.service;

import com.eximias.ecommerce.dto.OrdersDTO;
import com.eximias.ecommerce.entity.Orders;
import com.eximias.ecommerce.mapper.CustomerMapper;
import com.eximias.ecommerce.mapper.OrdersMapper;
import com.eximias.ecommerce.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdersService {
    private final OrdersMapper ordersMapper;
    private final CustomerMapper customerMapper;
    private final OrdersRepository ordersRepository;
    private CustomerService customerService;
    private ProductService productService;
    public Orders toEntity(Orders orders, OrdersDTO dto){
        dto.getOrderItemsList().stream().forEach(orderItems -> orderItems.setOrders(orders));
        orders.setCustomer(customerMapper.toEntity(dto.getCustomerDTO()));
        orders.setDelivery(dto.getDelivery());
        orders.setCreateAt(dto.getCreateAt());
        orders.getOrderItemsList().clear();
        orders.getOrderItemsList().addAll(dto.getOrderItemsList());
        return orders;
    }
    public int create(OrdersDTO dto){
        Orders orders = ordersMapper.toEntity(dto);
        return ordersRepository.save(orders).getId();
    }
    public List<OrdersDTO> toDto(List<Orders> ordersList){
        return ordersList.stream().map(ordersMapper::toDTO).collect(Collectors.toList());
    }
    public List<OrdersDTO> getAllOrders(){
        return toDto(ordersRepository.findAll());
    }
    public boolean delete(int id){
        if(ordersRepository.findById(id).isPresent()){
            ordersRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Optional<Orders> findOrderById(int id){
        return Optional.of(ordersRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Not found this order"));
    }
    public OrdersDTO update(int id, OrdersDTO ordersDTO){
        return ordersRepository.findById(id)
                .map(entity -> toEntity(entity, ordersDTO))
                .map(ordersRepository:: save)
                .map(ordersMapper::toDTO)
                .get();
    }
}
