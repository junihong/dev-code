package com.tamsil.springbootsqlinit.repository;

import com.tamsil.springbootsqlinit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
