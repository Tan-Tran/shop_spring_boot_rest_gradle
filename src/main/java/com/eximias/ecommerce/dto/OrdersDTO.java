package com.eximias.ecommerce.dto;

import com.eximias.ecommerce.entity.Customer;
import com.eximias.ecommerce.entity.OrderItems;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrdersDTO {
    private int id;
    private CustomerDTO customerDTO;
    private List<OrderItems> orderItemsList;
    private String delivery;
    private Date createAt;
}
