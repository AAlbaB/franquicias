package com.example.franquicias.repository;

import com.example.franquicias.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    Franchise findByName(String nombre);
}
