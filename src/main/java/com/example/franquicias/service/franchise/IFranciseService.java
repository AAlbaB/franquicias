package com.example.franquicias.service.franchise;

import com.example.franquicias.dto.ProductDTO;
import com.example.franquicias.model.Franchise;
import com.example.franquicias.request.FranchiseRequest;

import java.util.List;

public interface IFranciseService {
    Franchise addFranchise(FranchiseRequest franchise);
    Franchise updateFranchise(FranchiseRequest franchise, Long franchiseId);
    List<Franchise> getAllFranchises();
    List<ProductDTO> getProductsTopInventoryByBranch(String franchiseName);
}
