package com.tamsil.mocktest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private String phoneNumber;

    @Column
    private int experienceYears;

    @Builder
    public Teacher(String name, String gender, String phoneNumber, int experienceYears) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.experienceYears = experienceYears;
    }
}
