package com.tamsil.springtransaction.web.dto.order;

import com.tamsil.springtransaction.common.OrderStatus;
import com.tamsil.springtransaction.domain.customer.Customer;
import com.tamsil.springtransaction.domain.delivery.Delivery;
import com.tamsil.springtransaction.domain.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record OrdersResponseDto(
//        private Long id;
//
//        private Date orderDate;
//
//        @Enumerated(EnumType.STRING)
//        private OrderStatus orderStatus;
//
//        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//        @JoinColumn(name = "customer_id")
//        private Customer customer;
//
//        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//        @JoinColumn(name = "delivery_id")
//        private Delivery delivery;
//
//        @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
//        private List<Product> productList = new ArrayList<>();
) {
}
