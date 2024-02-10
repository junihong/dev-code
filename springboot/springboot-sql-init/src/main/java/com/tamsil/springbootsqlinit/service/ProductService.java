package com.tamsil.springbootsqlinit.service;

import com.tamsil.springbootsqlinit.entity.Product;
import com.tamsil.springbootsqlinit.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
