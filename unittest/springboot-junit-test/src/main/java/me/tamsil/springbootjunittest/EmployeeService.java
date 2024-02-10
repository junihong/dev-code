package me.tamsil.springbootjunittest;

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
        return savedEmployee.convert();
    }

    public EmployeeResponseDto update(Long id, EmployeeRequestDto requestDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("EX"));
        employee.update(requestDto);
        return employee.convert();
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(Employee::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("EX"));
        return employee.convert();
    }
}
