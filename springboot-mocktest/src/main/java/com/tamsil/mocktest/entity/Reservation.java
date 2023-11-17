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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String reservationNumber;

    @Column
    private LocalDate date;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @OneToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "FK_RESERVATION_MEMBER"))
    private Member member;

    @OneToOne
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "FK_RESERVATION_TEACHER"))
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "lesson_id", foreignKey = @ForeignKey(name = "FK_RESERVATION_LESSON"))
    private Lesson lesson;

    @Builder
    public Reservation(String reservationNumber, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, Member member, Teacher teacher, Lesson lesson) {
        this.reservationNumber = reservationNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.member = member;
        this.teacher = teacher;
        this.lesson = lesson;
    }
}
