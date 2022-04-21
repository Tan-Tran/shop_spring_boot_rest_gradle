package com.eximias.ecommerce.service;


import com.eximias.ecommerce.entity.OrderItems;
import com.eximias.ecommerce.repository.OrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public Optional<OrderItems> findById(int id){
        return Optional.of(orderItemsRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Not found order item"));
    }

    public boolean delete (int id){
        if(orderItemsRepository.findById(id).isPresent()){
            orderItemsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public int save(OrderItems orderItems){
        return orderItemsRepository.save(orderItems).getId();
    }

    public OrderItems update(int id, OrderItems orderItems){
        return orderItemsRepository.findById(id).map(orderItemsRepository::save).get();
    }
}
