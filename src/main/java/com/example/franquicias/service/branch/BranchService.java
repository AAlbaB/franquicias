package com.example.franquicias.service.branch;

import com.example.franquicias.exceptions.AlreadyExistsException;
import com.example.franquicias.exceptions.ResourceNotFoundException;
import com.example.franquicias.model.Branch;
import com.example.franquicias.model.Franchise;
import com.example.franquicias.repository.BranchRepository;
import com.example.franquicias.repository.FranchiseRepository;
import com.example.franquicias.request.AddBranchRequest;
import com.example.franquicias.request.BranchUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService implements IBranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    @Override
    public Branch addBranch(AddBranchRequest request) {
        return Optional.ofNullable(franchiseRepository.findByName(request.getFranchise().getName()))
                .map(franchise -> {
                    Optional.ofNullable(branchRepository.findByName(request.getBranchName()))
                            .ifPresent(branch -> {
                                throw new AlreadyExistsException("El nombre de la sucursal ya existe");
                            });
                    request.setFranchise(franchise);
                    return branchRepository.save(createBranch(request, franchise));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Franquicia no encontrada"));
    }

    private Branch createBranch(AddBranchRequest request, Franchise franchise) {
        return new Branch(
                request.getBranchName(),
                franchise
        );
    }

    @Override
    public Branch updateBranch(BranchUpdateRequest request, Long branchId) {
        return branchRepository.findById(branchId)
                .map(existingBranch -> updateExistingBranch(request, existingBranch))
                .map(branchRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada"));
    }

    private Branch updateExistingBranch(BranchUpdateRequest request, Branch existingBranch) {
        existingBranch.setName(request.getBranchName());
        return existingBranch;
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}
