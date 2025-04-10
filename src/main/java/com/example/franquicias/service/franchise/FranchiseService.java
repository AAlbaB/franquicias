package com.example.franquicias.service.franchise;

import com.example.franquicias.exceptions.AlreadyExistsException;
import com.example.franquicias.exceptions.ResourceNotFoundException;
import com.example.franquicias.model.Franchise;
import com.example.franquicias.repository.FranchiseRepository;
import com.example.franquicias.request.FranchiseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FranchiseService implements IFranciseService {

    private final FranchiseRepository franchiseRepository;

    @Override
    public Franchise addFranchise(FranchiseRequest request) {
        if (franchiseRepository.findByName(request.getFranchiseName()) != null) {
            throw new AlreadyExistsException("El nombre de la franchise ya existe");
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
}
