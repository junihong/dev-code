package com.tamsil.springtransaction.web.dto.product;

public record ProductResponseDto(
        Long id,
        String name,
        int price,
        int orderQuantity
) {
}
