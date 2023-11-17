package com.tamsil.springbootjpa;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Album extends Product {
    private String artist;
    private String etc;
}
