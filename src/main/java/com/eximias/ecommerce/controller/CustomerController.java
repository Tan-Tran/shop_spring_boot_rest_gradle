package com.eximias.ecommerce.controller;

import com.eximias.ecommerce.dto.CustomerDTO;
import com.eximias.ecommerce.entity.Customer;
import com.eximias.ecommerce.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping
    public List<CustomerDTO> getAll(){
        return customerService.getAllCustomer();
    }
    @GetMapping(path="/{id}")
    public Optional<Customer> findById(@PathVariable(name = "id") int id){
        return customerService.findCustomerById(id);
    }
    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable(name = "id") int id){
        return customerService.deleteById(id);
    }
    @PostMapping
    public int save(@RequestBody CustomerDTO customerDTO){
        return customerService.create(customerDTO);
    }
    @PutMapping(path="/{id}")
    public CustomerDTO update(@PathVariable(name= "id") int id, @RequestBody CustomerDTO customerDTO){
        return customerService.update(id, customerDTO);
    }

}
