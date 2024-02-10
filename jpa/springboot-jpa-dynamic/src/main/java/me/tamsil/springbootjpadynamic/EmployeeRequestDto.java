package me.tamsil.springbootjpadynamic;

import lombok.Data;

@Data
public class EmployeeRequestDto implements DefaultDto<Employee> {
    private String name;
    private String gender;
    private String phoneNumber;
    private String location;

    @Override
    public Employee convert() {
        return Employee.builder()
                .name(this.name)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .location(this.location)
                .build();
    }
}
