package com.eximias.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private int quantity;
    private String origin;
    private int price;
    private String description;
    private boolean display;
}
