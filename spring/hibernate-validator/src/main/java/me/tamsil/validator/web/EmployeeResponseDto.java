package me.tamsil.validator.web;

public record EmployeeResponseDto(
        long id,
        String name,
        String email,
        String phoneNumber,
        int age,
        String gender,
        String grade,
        String address
) {}