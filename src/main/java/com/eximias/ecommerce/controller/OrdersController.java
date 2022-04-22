package com.eximias.ecommerce.controller;
import com.eximias.ecommerce.dto.OrdersDTO;
import com.eximias.ecommerce.entity.Orders;
import com.eximias.ecommerce.service.OrdersService;
import com.eximias.ecommerce.service.PdfGenerateService;
import lombok.AllArgsConstructor;
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
    public Optional<Orders> printPd(@PathVariable(name ="id") int id){
        System.out.println("chay toi day");
        Map<String, Object> data = new HashMap<>();
        data.put("order", ordersService.findOrderById(id));
        pdfGenerateService.generatePdfFile("orderDetail", data);
        return ordersService.findOrderById(id);
    }

}
