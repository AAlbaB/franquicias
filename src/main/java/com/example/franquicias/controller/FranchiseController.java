package com.example.franquicias.controller;

import com.example.franquicias.exceptions.ResourceNotFoundException;
import com.example.franquicias.model.Franchise;
import com.example.franquicias.request.FranchiseRequest;
import com.example.franquicias.response.ApiResponse;
import com.example.franquicias.service.franchise.IFranciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/franchises")
public class FranchiseController {
    private final IFranciseService franchiseService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addFranchise(@RequestBody FranchiseRequest franchise) {
        try {
            Franchise theFranchise = franchiseService.addFranchise(franchise);
            return ResponseEntity.ok(new ApiResponse("Franquicia agregada!", theFranchise));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{franchiseId}/update")
    public ResponseEntity<ApiResponse> updateBranch(@RequestBody FranchiseRequest request, @PathVariable Long franchiseId) {
        try {
            Franchise theFranchise = franchiseService.updateFranchise(request, franchiseId);
            return ResponseEntity.ok(new ApiResponse("Franquicia actualizada!", theFranchise));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllBranches() {
        List<Franchise> franchises = franchiseService.getAllFranchises();
        return ResponseEntity.ok(new ApiResponse("success", franchises));
    }
}
