package com.tamsil.springbootjpa;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie extends Product {
    private String director;
    private String actor;
}
