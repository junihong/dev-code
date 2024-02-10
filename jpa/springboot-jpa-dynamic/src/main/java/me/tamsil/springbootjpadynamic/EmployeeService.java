package me.tamsil.springbootjpadynamic;

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
        return EmployeeResponseDto.builder()
                .id(savedEmployee.getId())
                .name(savedEmployee.getName())
                .gender(savedEmployee.getGender())
                .phoneNumber(savedEmployee.getPhoneNumber())
                .location(savedEmployee.getLocation())
                .build();
    }

    public EmployeeResponseDto update(Long id, EmployeeRequestDto requestDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("EX001"));
        employee.setName(requestDto.getName());
        employee.setGender(requestDto.getGender());
        employee.setPhoneNumber(requestDto.getPhoneNumber());
//        employee.setLocation(requestDto.getLocation());

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .gender(employee.getGender())
                .phoneNumber(employee.getPhoneNumber())
                .location(employee.getLocation())
                .build();
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> {
                    return EmployeeResponseDto.builder()
                            .id(employee.getId())
                            .name(employee.getName())
                            .gender(employee.getGender())
                            .phoneNumber(employee.getPhoneNumber())
                            .location(employee.getLocation())
                            .build();
                }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDto findById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("EX001"));
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .gender(employee.getGender())
                .phoneNumber(employee.getPhoneNumber())
                .location(employee.getLocation())
                .build();
    }
}
