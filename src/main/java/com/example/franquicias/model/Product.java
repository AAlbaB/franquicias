package com.example.franquicias.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer inventory;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Product(String productName, int inventory, Branch branch) {
        this.name = productName;
        this.inventory = inventory;
        this.branch = branch;
    }
}
