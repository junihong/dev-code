package me.tamsil.springdatajdbc.web;

public record CustomerRequestDto(
        String name,
        int age,
        String phoneNumber,
        String email,
        String address
) {
}
