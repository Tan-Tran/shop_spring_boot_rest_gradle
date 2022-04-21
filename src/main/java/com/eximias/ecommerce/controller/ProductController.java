package com.eximias.ecommerce.controller;

import com.eximias.ecommerce.dto.ProductDTO;
import com.eximias.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public List<ProductDTO> getAll(){
        return productService.getAllProduct();
    }
    @PostMapping
    public int save(@RequestBody ProductDTO productDTO){
        return productService.create(productDTO);
    }
    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable(name = "id") int id){
        return productService.delete(id);
    }
    @PutMapping(path="/{id}")
    public ProductDTO update(@PathVariable(name= "id") int id, @RequestBody ProductDTO productDTO){
        System.out.println(productDTO);
        return productService.update(id, productDTO);
    }

}
