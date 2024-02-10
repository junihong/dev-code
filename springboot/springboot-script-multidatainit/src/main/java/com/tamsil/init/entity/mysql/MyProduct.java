package com.tamsil.init.entity.mysql;

import com.tamsil.init.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class MyProduct extends BaseTimeEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productId;
    @Column
    private String company;
    @Column
    private String name;

    @Column
    private String category;

    @Column
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private MyOrder myOrder;
}
