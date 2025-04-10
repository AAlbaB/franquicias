package com.example.franquicias.dto;

import com.example.franquicias.model.Branch;
import com.example.franquicias.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private int inventory;
    private String branch;

    public ProductDTO(Product product, Branch branch) {
        this.productName = product.getName();
        this.inventory = product.getInventory();
        this.branch = branch.getName();
    }
}
