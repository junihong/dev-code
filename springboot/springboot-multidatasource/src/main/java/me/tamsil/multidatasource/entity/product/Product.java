package me.tamsil.multidatasource.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {

    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;
    private String company;
    private String category;
    private int amount;

    private double price;
}
