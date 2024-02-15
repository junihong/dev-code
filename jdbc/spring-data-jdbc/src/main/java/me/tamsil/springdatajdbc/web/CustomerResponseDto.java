package me.tamsil.springdatajdbc.web;

public record CustomerResponseDto(
        Long id,
        String name,
        int age,
        String phoneNumber,
        String email,
        String address
) {
}
