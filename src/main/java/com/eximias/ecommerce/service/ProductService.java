package com.eximias.ecommerce.service;

import com.eximias.ecommerce.dto.ProductDTO;
import com.eximias.ecommerce.entity.Product;
import com.eximias.ecommerce.mapper.ProductMapper;
import com.eximias.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public Product toEntity(Product product, ProductDTO productDTO){
        product.setQuantity(productDTO.getQuantity());
        product.setOrigin(productDTO.getOrigin());
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDisplay(productDTO.isDisplay());
        return product;
    }

    public List<ProductDTO> toDto(List<Product> products){
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    public int create(ProductDTO productDTO) {
        return productRepository.save(productMapper.toEntity(productDTO)).getId();
    }
    public Optional<Product> findProductById(int id){
        return Optional.of(productRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Not found this product"));
    }

    public boolean delete(int id){
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<ProductDTO> getAllProduct(){
        return toDto(productRepository.findAll());
    }

    public ProductDTO update(int id, ProductDTO productDTO){
        return productRepository.findById(id)
                .map(entity -> toEntity(entity, productDTO))
                .map(productRepository:: save)
                .map(productMapper::toDto)
                .get();
    }

}
