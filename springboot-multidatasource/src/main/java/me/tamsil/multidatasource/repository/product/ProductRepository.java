package me.tamsil.multidatasource.repository.product;

import me.tamsil.multidatasource.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
