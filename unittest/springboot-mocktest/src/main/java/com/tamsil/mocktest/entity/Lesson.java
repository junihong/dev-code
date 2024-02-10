package com.tamsil.mocktest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessons")
    private Teacher teacher;

    @Builder
    public Lesson(LocalDate date, LocalDateTime startTime, LocalDateTime endTime, Teacher teacher) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
    }
}
