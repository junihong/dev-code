package me.tamsil.springdatajdbc;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customers, Long> {
    List<Customers> findByName(String name);
}
