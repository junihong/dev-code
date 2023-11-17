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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@Slf4j
@ExtendWith(MockitoExtension.class)
class MemberServiceMockTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private ReservationJpaRepository reservationJpaRepository;

    @Mock
    private NotificationSender notificationSender;

    @Test
    void reserveLessonTest() throws Exception {
        LocalDate date = LocalDate.now();
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 1, 13, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 5, 1, 14, 0);

        Member member = getMember();
        Teacher teacher = getTeacher();
        Lesson lesson = getLesson(date, startTime, endTime, teacher);
        Reservation reservation = getReservation(startTime, endTime, member, teacher, lesson);

        given(lessonRepository.findByDateAndStartTimeAndEndTime(date, startTime, endTime)).willReturn(Optional.of(Collections.singletonList(lesson)));
        given(reservationJpaRepository.save(any())).willReturn(reservation);
        given(notificationSender.sendMessage(anyString(), any())).willReturn("SUCCESS");

        Reservation savedReservation = memberService.reserveLesson(member, date, startTime, endTime);

        then(lessonRepository).should(times(1)).findByDateAndStartTimeAndEndTime(date, startTime, endTime);
        then(reservationJpaRepository).should(times(1)).save(any());
        then(notificationSender).should(times(1)).sendMessage(anyString(), any());
    }

    private static Reservation getReservation(LocalDateTime startTime, LocalDateTime endTime, Member member, Teacher teacher, Lesson lesson) {
        Reservation reservation = Reservation.builder()
                .reservationNumber(ApplicationUtil.generateId())
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .teacher(teacher)
                .lesson(lesson)
                .build();
        return reservation;
    }

    private static Lesson getLesson(LocalDate date, LocalDateTime startTime, LocalDateTime endTime, Teacher teacher) {
        Lesson lesson = Lesson.builder()
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .teacher(teacher)
                .build();
        return lesson;
    }

    private static Teacher getTeacher() {
        Teacher teacher = Teacher.builder()
                .name("john")
                .gender("MALE")
                .phoneNumber("000-0000-0000")
                .experienceYears(8)
                .build();
        return teacher;
    }

    private static Member getMember() {
        Member member = Member.builder()
                .name("tamsil")
                .phoneNumber("000-0000-0000")
                .address("Seoul/Korea")
                .age(30)
                .gender("MALE")
                .build();
        return member;
    }
}
