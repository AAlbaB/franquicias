package com.example.franquicias.request;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String productName;
    private int inventory;
}
