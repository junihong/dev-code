package me.tamsil.springbootjunittest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto responseDto = employeeService.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto responseDto = employeeService.update(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
