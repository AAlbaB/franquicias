package com.example.franquicias.request;

import com.example.franquicias.model.Branch;
import lombok.Data;

@Data
public class AddProductRequest {
    private Long id;
    private String productName;
    private int inventory;
    private Branch branch;
}
