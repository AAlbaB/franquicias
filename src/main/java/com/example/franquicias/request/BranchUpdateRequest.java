package com.example.franquicias.request;

import lombok.Data;

@Data
public class BranchUpdateRequest {
    private Long id;
    private String branchName;
}
