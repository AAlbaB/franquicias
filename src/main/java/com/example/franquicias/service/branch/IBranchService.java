package com.example.franquicias.service.branch;

import com.example.franquicias.model.Branch;
import com.example.franquicias.request.AddBranchRequest;
import com.example.franquicias.request.BranchUpdateRequest;

import java.util.List;

public interface IBranchService {
    Branch addBranch(AddBranchRequest branch);
    Branch updateBranch(BranchUpdateRequest branch, Long branchId);
    List<Branch> getAllBranches();
}
