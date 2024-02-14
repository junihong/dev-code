package me.tamsil.springdatajdbc;

public record CustomerRequestDto(
        String name,
        int age,
        String phoneNumber,
        String email,
        String address
) {
}
