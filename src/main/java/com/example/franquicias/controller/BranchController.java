package com.example.franquicias.controller;

import com.example.franquicias.exceptions.ResourceNotFoundException;
import com.example.franquicias.model.Branch;
import com.example.franquicias.request.AddBranchRequest;
import com.example.franquicias.request.BranchUpdateRequest;
import com.example.franquicias.response.ApiResponse;
import com.example.franquicias.service.branch.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/branches")
public class BranchController {
    private final IBranchService branchService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBranch(@RequestBody AddBranchRequest branch) {
        try {
            Branch theBranch = branchService.addBranch(branch);
            return ResponseEntity.ok(new ApiResponse("Sucursal agregada!", theBranch));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{branchId}/update")
    public ResponseEntity<ApiResponse> updateBranch(@RequestBody BranchUpdateRequest request, @PathVariable Long branchId) {
        try {
            Branch theBranch = branchService.updateBranch(request, branchId);
            return ResponseEntity.ok(new ApiResponse("Sucursal actualizada!", theBranch));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return ResponseEntity.ok(new ApiResponse("success", branches));
    }
}
