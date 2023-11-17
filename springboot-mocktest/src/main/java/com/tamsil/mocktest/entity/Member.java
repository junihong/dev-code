package com.tamsil.mocktest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private int age;

    @Column
    private String gender;

    @Builder
    public Member(String name, String phoneNumber, String address, int age, String gender) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }
}
