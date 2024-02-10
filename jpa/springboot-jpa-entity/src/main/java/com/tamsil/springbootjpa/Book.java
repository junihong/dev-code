package com.tamsil.springbootjpa;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends Product {
    private String author;
    private String isbn;
}
