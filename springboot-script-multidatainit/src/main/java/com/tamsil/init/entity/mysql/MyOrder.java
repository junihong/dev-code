package com.tamsil.init.entity.mysql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "order")
@Data
public class MyOrder {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "myOrder")
    private List<MyProduct> myProductList;
}
