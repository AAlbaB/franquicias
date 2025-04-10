package com.example.franquicias.repository;

import com.example.franquicias.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findByName(String name);
}
