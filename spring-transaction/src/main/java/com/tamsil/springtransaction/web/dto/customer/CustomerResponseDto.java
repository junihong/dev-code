package com.tamsil.springtransaction.web.dto.customer;

import com.tamsil.springtransaction.domain.customer.Customer;

public record CustomerResponseDto(
        Long id,
        String name,
        int age,
        String gender,
        String phoneNumber,
        String email,
        String address
) {
}
