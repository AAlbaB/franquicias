package com.example.franquicias.service.product;

import com.example.franquicias.exceptions.ResourceNotFoundException;
import com.example.franquicias.model.Branch;
import com.example.franquicias.model.Product;
import com.example.franquicias.repository.BranchRepository;
import com.example.franquicias.repository.ProductRepository;
import com.example.franquicias.request.AddProductRequest;
import com.example.franquicias.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        return Optional.ofNullable(branchRepository.findByName(request.getBranch().getName()))
                .map(branch -> {
                    request.setBranch(branch);
                    return productRepository.save(createProduct(request, branch));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal no encontrada"));
    }

    private Product createProduct(AddProductRequest request, Branch branch) {
        return new Product(
                request.getProductName(),
                request.getInventory(),
                branch
        );
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(request, existingProduct))
                .map(productRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    private Product updateExistingProduct(ProductUpdateRequest request, Product existingProduct) {
        existingProduct.setName(request.getProductName());
        existingProduct.setInventory(request.getInventory());

        return existingProduct;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ResourceNotFoundException("Producto no encontrado");
                });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getMaxProductsByFranchise(Long franchiseId) {
        return List.of();
    }
}
