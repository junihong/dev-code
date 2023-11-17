package com.tamsil.springtransaction.domain.customer;

import com.tamsil.springtransaction.web.dto.customer.CustomerRequestDto;
import com.tamsil.springtransaction.web.dto.customer.CustomerResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDto create(CustomerRequestDto requestDto) {
        Customer savedCustomer = customerRepository.save(requestDto.toEntity());
        log.info("Saved : {}", savedCustomer);
        return new CustomerResponseDto(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getAge(),
                savedCustomer.getGender(),
                savedCustomer.getPhoneNumber(),
                savedCustomer.getEmail(),
                savedCustomer.getAddress()
        );
    }

    public CustomerResponseDto update(long id, CustomerRequestDto requestDto) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.update(requestDto);
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getGender(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

    public CustomerResponseDto findById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getGender(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

    public List<CustomerResponseDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerResponseDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getAge(),
                        customer.getGender(),
                        customer.getPhoneNumber(),
                        customer.getEmail(),
                        customer.getAddress()
                ))
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        customerRepository.deleteById(id);
    }
}
