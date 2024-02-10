package me.tamsil.springbootjunittest;

import lombok.Data;

@Data
public class EmployeeRequestDto implements DefaultDto<Employee> {

    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String birth;
    private String address;

    @Override
    public Employee convert() {
        return Employee.builder()
                .name(this.name)
                .age(this.age)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .birth(this.birth)
                .address(this.address)
                .build();
    }
}
