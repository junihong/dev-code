package com.tamsil.init.repository.postgresql;

import com.tamsil.init.entity.postgresql.PostProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostProductRepository extends JpaRepository<PostProduct, Long> {
}
