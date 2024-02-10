package me.tamsil.springbootjpadynamic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<EmployeeResponseDto> employeeResponseDtoList = employeeService.findAll();
        return new ResponseEntity<>(employeeResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        EmployeeResponseDto employeeResponseDto = employeeService.findById(id);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto responseDto = employeeService.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody EmployeeRequestDto requestDto) {
        EmployeeResponseDto responseDto = employeeService.update(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
