package com.tamsil.springtransaction.web.dto.customer;

import com.tamsil.springtransaction.domain.customer.Customer;

public record CustomerRequestDto(
        String name,
        int age,
        String gender,
        String phoneNumber,
        String email,
        String address
        ) {
    public Customer toEntity() {
        return Customer.builder()
                .name(this.name)
                .age(this.age)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .address(this.address)
                .build();
    }
}
