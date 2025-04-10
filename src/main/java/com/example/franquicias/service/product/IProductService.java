package com.example.franquicias.service.product;

import com.example.franquicias.model.Product;
import com.example.franquicias.request.AddProductRequest;
import com.example.franquicias.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product updateProduct(ProductUpdateRequest product, Long productId);

    Product updateProduct(ProductUpdateRequest request, Long productId);

    void deleteProduct(Long productId);
    List<Product> getAllProducts();
    List<Product> getMaxProductsByFranchise(Long franchiseId);
}
