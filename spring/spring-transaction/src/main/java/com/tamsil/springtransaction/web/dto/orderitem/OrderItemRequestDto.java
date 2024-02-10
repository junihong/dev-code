package com.tamsil.springtransaction.web.dto.orderitem;

import com.tamsil.springtransaction.web.dto.order.OrdersRequestDto;

import java.util.List;

public record OrderItemRequestDto(
        long productId,
        int quantity,
        List<OrdersRequestDto> ordersRequestDtoList
) {
}
