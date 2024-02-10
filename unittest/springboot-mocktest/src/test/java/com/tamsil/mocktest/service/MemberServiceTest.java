package com.tamsil.mocktest.service;

import com.tamsil.mocktest.entity.Lesson;
import com.tamsil.mocktest.entity.Member;
import com.tamsil.mocktest.entity.Reservation;
import com.tamsil.mocktest.entity.Teacher;
import com.tamsil.mocktest.repository.jpa.LessonRepository;
import com.tamsil.mocktest.repository.jpa.MemberJpaRepository;
import com.tamsil.mocktest.repository.jpa.ReservationJpaRepository;
import com.tamsil.mocktest.repository.jpa.TeacherJpaRepository;
import com.tamsil.mocktest.util.ApplicationUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
class MemberServiceTest {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private MemberJpaRepository memberJpaRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private TeacherJpaRepository teacherJpaRepository;
    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @Test
    void reserveLessonTest() throws Exception {
        LocalDate date = LocalDate.now();
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 1, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 1, 14, 0);

        Member member = Member.builder()
                .name("tamsil")
                .phoneNumber("000-0000-0000")
                .address("Seoul/Korea")
                .age(30)
                .gender("MALE")
                .build();
        memberJpaRepository.save(member);

        // Teacher
        Teacher teacher = Teacher.builder()
                .name("john")
                .gender("MALE")
                .phoneNumber("000-0000-0000")
                .experienceYears(8)
                .build();
        teacherJpaRepository.save(teacher);

        // Lesson
        Lesson lesson = Lesson.builder()
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .teacher(teacher)
                .build();
        lessonRepository.save(lesson);

        Reservation reservation = Reservation.builder()
                .reservationNumber(ApplicationUtil.generateId())
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .teacher(teacher)
                .lesson(lesson)
                .build();
        Reservation savedReservation = reservationJpaRepository.save(reservation);
        log.info("savedReservation : {}", savedReservation.getReservationNumber());

        httpSession.setAttribute("member", member);
    }
}