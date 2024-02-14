package me.tamsil.springdatajdbc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<? extends CustomerResponseDto> create(@RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto customerResponseDto = customerService.create(requestDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<? extends CustomerResponseDto> findById(@PathVariable Long id) {
        CustomerResponseDto customerResponseDto = customerService.findById(id);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<? extends List<CustomerResponseDto>> findAll() {
        List<CustomerResponseDto> customerResponseDtoList = customerService.findAll();
        return new ResponseEntity<>(customerResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<? extends String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
