package me.tamsil.springdatajdbc.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customers, Long> {
    List<Customers> findByName(String name);

    List<Customers> findByAddress(String address);

    List<Customers> findByNameAndEmail(String name, String email);

    @Modifying
    @Query("UPDATE customers SET name = :name WHERE id = :id")
    boolean updateByName(@Param("id") Long id, @Param("name") String name);
}
