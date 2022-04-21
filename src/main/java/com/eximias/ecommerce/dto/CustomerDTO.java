package com.eximias.ecommerce.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private Date dateOfBirth;
    private String phone;
}
