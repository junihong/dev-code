package com.tamsil.springtransaction.domain.customer;

import com.tamsil.springtransaction.web.dto.customer.CustomerRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;

    public void update(CustomerRequestDto requestDto) {
        this.name = (StringUtils.hasText(requestDto.name()) ? requestDto.name() : this.name);
        this.age = (age > 0) ? requestDto.age() : this.age;
        this.gender = (StringUtils.hasText(requestDto.gender()) ? requestDto.gender() : this.getGender());
        this.phoneNumber = (StringUtils.hasText(requestDto.phoneNumber()) ? requestDto.phoneNumber() : this.phoneNumber);
        this.email = (StringUtils.hasText(requestDto.email()) ? requestDto.email() : this.email);
        this.address = (StringUtils.hasText(requestDto.address()) ? requestDto.address() : this.address);
    }
}
