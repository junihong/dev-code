package me.tamsil.springdatajdbc.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customers {
    @Id
    private long id;
    private String name;
    private int age;
    private String phoneNumber;
    private String email;
    private String address;

    @Builder
    public Customers(String name, int age, String phoneNumber, String email, String address) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
