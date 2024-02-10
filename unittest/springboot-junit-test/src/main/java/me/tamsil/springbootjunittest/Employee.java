package me.tamsil.springbootjunittest;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Optional;

@DynamicInsert
@DynamicUpdate
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee implements DefaultEntity<EmployeeResponseDto, EmployeeRequestDto>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String birth;
    private String address;

    @Builder
    public Employee(String name, int age, String gender, String phoneNumber, String birth, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.address = address;
    }

    @Override
    public EmployeeResponseDto convert() {
        return EmployeeResponseDto.builder()
                .id(this.id)
                .age(this.age)
                .gender(this.gender)
                .birth(this.birth)
                .address(this.address)
                .build();
    }

    @Override
    public void update(EmployeeRequestDto employeeRequestDto) {
        this.name = Optional.ofNullable(employeeRequestDto.getName()).orElse(this.name);
        this.age = employeeRequestDto.getAge() > 0 ? employeeRequestDto.getAge() : this.age;
        this.gender = Optional.ofNullable(employeeRequestDto.getGender()).orElse(this.gender);
        this.phoneNumber = Optional.ofNullable(employeeRequestDto.getPhoneNumber()).orElse(this.phoneNumber);
        this.birth = Optional.ofNullable(employeeRequestDto.getBirth()).orElse(this.birth);
        this.address = Optional.ofNullable(employeeRequestDto.getAddress()).orElse(this.address);
    }
}
