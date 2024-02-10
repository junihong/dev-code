package com.tamsil.springtransaction.web;

import com.tamsil.springtransaction.domain.customer.CustomerService;
import com.tamsil.springtransaction.web.dto.customer.CustomerRequestDto;
import com.tamsil.springtransaction.web.dto.customer.CustomerResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public CustomerResponseDto create(@RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto customerResponseDto = customerService.create(requestDto);
        log.info("Customer create result : {}", customerResponseDto);
        return customerResponseDto;
    }

    @PutMapping("/update/{id}")
    public CustomerResponseDto update(@PathVariable Long id, @RequestBody CustomerRequestDto requestDto) {
        CustomerResponseDto customerResponseDto = customerService.update(id, requestDto);
        log.info("Customer update result : {}", customerResponseDto);
        return customerResponseDto;
    }

    @GetMapping("/findAll")
    public List<CustomerResponseDto> findAll() {
        List<CustomerResponseDto> customerResponseDtoList = customerService.findAll();
        log.info("Customer findAll result count : {}", customerResponseDtoList.size());
        return customerResponseDtoList;
    }

    @GetMapping("/find/{id}")
    public CustomerResponseDto findById(@PathVariable Long id) {
        CustomerResponseDto customerResponseDto = customerService.findById(id);
        log.info("Customer findById result : {}", customerResponseDto);
        return customerResponseDto;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
