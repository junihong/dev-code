package com.tamsil.springtransaction.web.dto.orderitem;

import com.tamsil.springtransaction.web.dto.order.OrdersResponseDto;
import com.tamsil.springtransaction.web.dto.product.ProductResponseDto;

public record OrderItemResponseDto(
        Long id,
        ProductResponseDto productResponseDto,
        int quantity,
        OrdersResponseDto ordersResponseDto
) {
}
