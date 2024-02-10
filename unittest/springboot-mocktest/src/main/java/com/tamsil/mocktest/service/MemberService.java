package com.tamsil.mocktest.service;

import com.tamsil.mocktest.NotificationSender;
import com.tamsil.mocktest.entity.Lesson;
import com.tamsil.mocktest.entity.Member;
import com.tamsil.mocktest.entity.Reservation;
import com.tamsil.mocktest.entity.Teacher;
import com.tamsil.mocktest.repository.jpa.LessonRepository;
import com.tamsil.mocktest.repository.jpa.MemberJpaRepository;
import com.tamsil.mocktest.repository.jpa.ReservationJpaRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final LessonRepository lessonRepository;
    private final ReservationJpaRepository reservationJpaRepository;
    private final NotificationSender notificationSender;
    private final TeacherService teacherService;
    private final HttpSession httpSession;

    public Member findByMember(String name) {
        return memberJpaRepository.findByName(name).orElseThrow(() -> new EmptyResultDataAccessException("해당 멤버가 존재하지 않습니다.", 0));
    }

    public Reservation reserveLesson(Member member, LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
        List<Lesson> lessonList = lessonRepository.findByDateAndStartTimeAndEndTime(date, startTime, endTime).orElseThrow(() -> new EmptyResultDataAccessException("조건에 해당하는 레슨이 없습니다.", 0));
        Lesson findLesson = lessonList.stream()
                .filter(lesson -> Objects.nonNull(lesson))
                .findFirst()
                .orElseThrow(() -> new EmptyResultDataAccessException("조건에 해당하는 레슨이 없습니다.", 0));
        Reservation reservation = Reservation.builder()
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .lesson(findLesson)
                .build();
        Reservation savedReservation = reservationJpaRepository.save(reservation);
        notificationSender.sendMessage(("RESERVATION-0001"), savedReservation);

        return savedReservation;
    }

    public Reservation reserveLesson(Member member, LocalDate date, LocalDateTime startTime, LocalDateTime endTime, String teacherName) {
        Teacher teacher = Optional.ofNullable(teacherService.findByName(teacherName))
                .orElseGet(() -> Optional.of(teacherService.create(Teacher.builder()
                        .name(teacherName)
                        .experienceYears(1)
                        .phoneNumber("000-0000-0000")
                        .gender("MALE")
                        .build())))
                .get();
        Lesson lesson = lessonRepository.findByDateAndStartTimeAndEndTimeAndTeacher(date, startTime, endTime, teacher).orElseThrow();
        Reservation reservation = Reservation.builder()
                .startTime(startTime)
                .endTime(endTime)
                .date(date)
                .teacher(teacher)
                .member(member)
                .lesson(lesson)
                .build();
        Reservation savedReservation = reservationJpaRepository.save(reservation);
        notificationSender.sendMessage("RESERVATION-0001", savedReservation);

        return savedReservation;
    }

    public void saveMyLessonSession() {
        Member member = (Member) httpSession.getAttribute("member");
        List<Reservation> reservations = reservationJpaRepository.findByMember(member).orElse(Collections.emptyList());
        httpSession.setAttribute("reservationCount", reservations.size());
    }

    public List<Reservation> getMyReservation(Member member) {
        return reservationJpaRepository.findByMember(member).orElse(Collections.emptyList());
    }
}
