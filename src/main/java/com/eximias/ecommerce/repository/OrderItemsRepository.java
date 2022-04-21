package com.eximias.ecommerce.repository;


import com.eximias.ecommerce.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findOrderItemsByOrdersId(int orderId);
}
