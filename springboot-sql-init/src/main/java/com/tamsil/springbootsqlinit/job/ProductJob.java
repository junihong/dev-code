package com.tamsil.springbootsqlinit.job;

import com.tamsil.springbootsqlinit.entity.Product;
import com.tamsil.springbootsqlinit.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductJob implements Job {

    private final ProductService productService;

    public ProductJob(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        productService.findAll()
                .stream().map(Product::toString)
                .forEach(log::info);
    }
}
