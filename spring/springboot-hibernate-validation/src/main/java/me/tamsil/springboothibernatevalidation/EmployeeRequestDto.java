package me.tamsil.springboothibernatevalidation;

import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "PhoneNumber is mandatory")
    private String phoneNumber;

    private int age;
    private String gender;
    private String grade;
    private String address;


}
