package me.tamsil.validator.service;

import me.tamsil.validator.web.EmployeeRequestDto;
import me.tamsil.validator.web.EmployeeResponseDto;
import me.tamsil.validator.domain.Employee;
import me.tamsil.validator.domain.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDto create(EmployeeRequestDto requestDto) {
        Employee employee = requestDto.convert();
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeResponseDto(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getEmail(),
                savedEmployee.getPhoneNumber(),
                savedEmployee.getAge(),
                savedEmployee.getGender(),
                savedEmployee.getGrade(),
                savedEmployee.getAddress()
        );
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .map(employee -> {
                    return new EmployeeResponseDto(
                            employee.getId(),
                            employee.getName(),
                            employee.getEmail(),
                            employee.getPhoneNumber(),
                            employee.getAge(),
                            employee.getGender(),
                            employee.getGrade(),
                            employee.getAddress()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("EX"));
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getAge(),
                employee.getGender(),
                employee.getGrade(),
                employee.getAddress()
        );
    }
}
