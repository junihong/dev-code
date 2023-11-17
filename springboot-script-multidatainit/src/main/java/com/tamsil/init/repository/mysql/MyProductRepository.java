package com.tamsil.init.repository.mysql;

import com.tamsil.init.entity.mysql.MyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyProductRepository extends JpaRepository<MyProduct, Long> {
}
