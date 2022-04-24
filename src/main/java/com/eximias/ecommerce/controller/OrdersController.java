package com.eximias.ecommerce.controller;
import com.eximias.ecommerce.dto.OrdersDTO;
import com.eximias.ecommerce.entity.Orders;
import com.eximias.ecommerce.mapper.CustomerMapper;
import com.eximias.ecommerce.service.OrdersService;
import com.eximias.ecommerce.service.PdfGenerateService;
import com.eximias.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrdersController {
    private final OrdersService ordersService;
    private final PdfGenerateService pdfGenerateService;
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
    @GetMapping(path ="/{id}/pdf")
    public ResponseEntity<byte[]> printPd(@PathVariable(name ="id") int id){
        Orders orders = ordersService.findOrderById(id).get();
        Map<String, Object> data = new HashMap<>();
        data.put("order", orders);
        return pdfGenerateService.generateOrderDetailPdfFile("orderDetailThymeLeaf", data);
    }
}
