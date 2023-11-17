package me.tamsil.multidatasource.controller;

import lombok.RequiredArgsConstructor;
import me.tamsil.multidatasource.entity.product.Product;
import me.tamsil.multidatasource.repository.product.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/findAll")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }
}
