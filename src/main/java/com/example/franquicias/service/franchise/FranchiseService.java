package com.example.franquicias.service.franchise;

import com.example.franquicias.dto.ProductDTO;
import com.example.franquicias.exceptions.AlreadyExistsException;
import com.example.franquicias.exceptions.ResourceNotFoundException;
import com.example.franquicias.model.Franchise;
import com.example.franquicias.model.Product;
import com.example.franquicias.repository.FranchiseRepository;
import com.example.franquicias.request.FranchiseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FranchiseService implements IFranciseService {

    private final FranchiseRepository franchiseRepository;

    @Override
    public Franchise addFranchise(FranchiseRequest request) {
        if (franchiseRepository.findByName(request.getFranchiseName()) != null) {
            throw new AlreadyExistsException("El nombre de la franquicia ya existe");
        }
        return franchiseRepository.save(createFranchise(request));
    }

    private Franchise createFranchise(FranchiseRequest request) {
        return new Franchise(
                request.getFranchiseName()
        );
    }

    @Override
    public Franchise updateFranchise(FranchiseRequest request, Long franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .map(existingFranchise -> updateExistingFranchise(request, existingFranchise))
                .map(franchiseRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Franquicia no encontrada"));
    }

    private Franchise updateExistingFranchise(FranchiseRequest request, Franchise existingFranchise) {
        existingFranchise.setName(request.getFranchiseName());
        return existingFranchise;
    }

    @Override
    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    @Override
    public List<ProductDTO> getProductsTopInventoryByBranch(String franchiseName) {
        Franchise franchise = franchiseRepository.findByName(franchiseName);
        if (franchise == null) {
            throw new ResourceNotFoundException("Franquicia no encontrada");
        }

        return franchise.getBranches().stream()
                .map(branch -> branch.getProducts().stream()
                        .max(Comparator.comparingInt(Product::getInventory))
                        .map(producto -> new ProductDTO(producto.getName(), producto.getInventory(), branch.getName()))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
