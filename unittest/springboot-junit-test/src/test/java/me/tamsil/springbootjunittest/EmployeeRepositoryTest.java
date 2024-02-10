package me.tamsil.springbootjunittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void insertEmployee() {
        Employee employee = Employee.builder()
                .name("Test01")
                .age(40)
                .gender("MAIL")
                .phoneNumber("010-0000-0000")
                .birth("2024-01-01")
                .address("Seoul")
                .build();
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println(savedEmployee);
    }
}