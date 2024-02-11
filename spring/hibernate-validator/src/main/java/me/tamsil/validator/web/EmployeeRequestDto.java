package me.tamsil.validator.web;

import jakarta.validation.constraints.*;
import lombok.*;
import me.tamsil.validator.comon.DefaultDto;
import me.tamsil.validator.domain.Employee;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeRequestDto implements DefaultDto<Employee> {

    @NotBlank(message = "Name is not blank")
    @NotNull(message = "Name is not null")
    @Size(min = 1, max = 100, message = "Name must be of 1 to 100 characters")
    private String name;

    @NotBlank(message = "Email is not blank")
    private String email;

    @NotBlank(message = "PhoneNumber is mandatory")
    @Pattern(regexp = "^\\\\d{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @Min(value = 1, message = "Age must be over 1")
    @Max(value = 100, message = "Age must smaller than 100")
    private Integer age;
    private String gender;
    private String grade;
    private String address;

    @Builder
    public EmployeeRequestDto(String name, String email, String phoneNumber, int age, String gender, String grade, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.address = address;
    }

    @Override
    public Employee convert() {
        return Employee.builder()
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .age(this.age)
                .gender(this.gender)
                .grade(this.grade)
                .address(this.address)
                .build();
    }
}
