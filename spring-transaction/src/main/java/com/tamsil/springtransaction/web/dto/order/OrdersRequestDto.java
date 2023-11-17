package com.tamsil.springtransaction.web.dto.order;

import com.tamsil.springtransaction.common.OrderStatus;
import com.tamsil.springtransaction.domain.product.Product;

import java.util.Date;
import java.util.List;

public record OrdersRequestDto(
        Date orderDate,
        OrderStatus orderStatus,
        String customerId,
        List<Product> productList
) {
}
