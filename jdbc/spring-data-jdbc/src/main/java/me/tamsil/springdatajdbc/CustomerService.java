package me.tamsil.springdatajdbc;

import org.springframework.stereotype.Service;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDto create(CustomerRequestDto requestDto) {
        var customer = Customers.builder()
                .name(requestDto.name())
                .age(requestDto.age())
                .phoneNumber(requestDto.phoneNumber())
                .email(requestDto.email())
                .address(requestDto.address())
                .build();
        var savedCustomer = customerRepository.save(customer);
        return new CustomerResponseDto(
                savedCustomer.getId(), savedCustomer.getName(), savedCustomer.getAge(),
                savedCustomer.getPhoneNumber(), savedCustomer.getEmail(), savedCustomer.getAddress()
        );
    }

    public CustomerResponseDto findById(Long id) {
        var customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("EX"));
        return new CustomerResponseDto(
                customer.getId(), customer.getName(), customer.getAge(),
                customer.getPhoneNumber(), customer.getEmail(), customer.getAddress()
        );
    }

    public List<CustomerResponseDto> findByName(String name) {
        List<Customers> customerList = customerRepository.findByName(name);
        List<CustomerResponseDto> customerResponseDtoList = customerList.stream()
                .map(customers -> {
                    return new CustomerResponseDto(
                            customers.getId(),
                            customers.getName(),
                            customers.getAge(),
                            customers.getPhoneNumber(),
                            customers.getEmail(),
                            customers.getAddress()
                    );
                })
                .collect(Collectors.toList());
        return customerResponseDtoList;
    }

    public boolean updateByName(Long id, String name) {
        return customerRepository.updateByName(id, name);
    }

    public List<CustomerResponseDto> findAll() {
        Iterator<Customers> iterator = customerRepository.findAll().iterator();
        List<CustomerResponseDto> responseDtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            Customers customers = iterator.next();
            var customerResponseDto = new CustomerResponseDto(customers.getId(), customers.getName(), customers.getAge(),
                    customers.getPhoneNumber(), customers.getEmail(), customers.getAddress());
            responseDtoList.add(customerResponseDto);
        }
        return responseDtoList;
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
