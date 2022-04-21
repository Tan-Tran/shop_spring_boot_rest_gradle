package com.eximias.ecommerce.controller;
import com.eximias.ecommerce.dto.OrdersDTO;
import com.eximias.ecommerce.entity.Orders;
import com.eximias.ecommerce.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrdersController {
    private final OrdersService ordersService;
    @PostMapping(path = "/new")
    public int create(@RequestBody OrdersDTO ordersDTO){
       return ordersService.create(ordersDTO);
    }
    @GetMapping
    public List<OrdersDTO> getAll(){
        return ordersService.getAllOrders();
    }
    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable(name="id") int id){
        return ordersService.delete(id);
    }
    @PutMapping(path = "/{id}")
    public OrdersDTO update(@PathVariable(name="id") int id, @RequestBody OrdersDTO ordersDTO){
        return ordersService.update(id, ordersDTO);
    }
    @GetMapping(path = "/{id}")
    public Optional<Orders> findById(@PathVariable(name ="id") int id){
        return ordersService.findOrderById(id);
    }

}
