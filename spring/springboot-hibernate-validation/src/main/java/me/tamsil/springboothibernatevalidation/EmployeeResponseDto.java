package me.tamsil.springboothibernatevalidation;

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