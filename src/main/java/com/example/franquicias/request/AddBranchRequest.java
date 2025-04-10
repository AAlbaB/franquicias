package com.example.franquicias.request;

import com.example.franquicias.model.Franchise;
import lombok.Data;

@Data
public class AddBranchRequest {
    private Long id;
    private String branchName;
    private Franchise franchise;
}
