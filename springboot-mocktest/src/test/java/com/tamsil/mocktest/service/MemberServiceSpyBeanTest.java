package com.tamsil.mocktest.service;

import com.tamsil.mocktest.NotificationSender;
import com.tamsil.mocktest.entity.Lesson;
import com.tamsil.mocktest.entity.Member;
import com.tamsil.mocktest.entity.Reservation;
import com.tamsil.mocktest.entity.Teacher;
import com.tamsil.mocktest.repository.jpa.LessonRepository;
import com.tamsil.mocktest.repository.jpa.ReservationJpaRepository;
import com.tamsil.mocktest.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@Slf4j
class MemberServiceSpyBeanTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private LessonRepository lessonRepository;

    @SpyBean
    private TeacherService teacherService;

    @MockBean
    private ReservationJpaRepository reservationJpaRepository;

    @MockBean
    private NotificationSender notificationSender;

    @Test
    void reserveLessonTest() {
        LocalDate date = LocalDate.now();
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 1, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 1, 14, 0);
        String teacherName = "tamsil";

        Member member = Member.builder()
                .name("tamsil")
                .phoneNumber("000-0000-0000")
                .address("Seoul/Korea")
                .age(30)
                .gender("MALE")
                .build();

        // Teacher
        Teacher teacher = Teacher.builder()
                .name("john")
                .gender("MALE")
                .phoneNumber("000-0000-0000")
                .experienceYears(8)
                .build();

        // Lesson
        Lesson lesson = Lesson.builder()
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .teacher(teacher)
                .build();

        Reservation reservation = Reservation.builder()
                .reservationNumber(ApplicationUtil.generateId())
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .teacher(teacher)
                .lesson(lesson)
                .build();

        given(lessonRepository.findByDateAndStartTimeAndEndTimeAndTeacher(any(), any(), any(), any())).willReturn(Optional.of(lesson));
        given(reservationJpaRepository.save(any())).willReturn(reservation);
        given(teacherService.findByName(anyString())).willReturn(null);
        given(notificationSender.sendMessage(anyString(), any())).willReturn("SUCCESS");

        Reservation savedReservation = memberService.reserveLesson(member, date, startTime, endTime, teacherName);
        log.info("savedReservation : {}", savedReservation.getReservationNumber());

        then(lessonRepository).should(times(1)).findByDateAndStartTimeAndEndTime(any(), any(), any());
        then(reservationJpaRepository).should(times(1)).save(any());
        then(notificationSender).should(times(1)).sendMessage(anyString(), any());
    }
}
