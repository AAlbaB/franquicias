package com.example.franquicias.repository;

import com.example.franquicias.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    //List<Product> findTopByBranchIdOrderByInventoryDesc(Long branchId);
}
