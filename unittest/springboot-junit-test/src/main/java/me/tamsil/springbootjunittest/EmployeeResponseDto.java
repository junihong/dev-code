package me.tamsil.springbootjunittest;

import lombok.Builder;
import lombok.Data;

@Data
public class EmployeeResponseDto {
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String birth;
    private String address;

    @Builder
    public EmployeeResponseDto(Long id, String name, int age, String gender, String phoneNumber, String birth, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.address = address;
    }
}
