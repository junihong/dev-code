package com.tamsil.springtransaction.web.dto.product;

public record ProductRequestDto(
        String name,
        int price,
        int orderQuantity
) {
}
