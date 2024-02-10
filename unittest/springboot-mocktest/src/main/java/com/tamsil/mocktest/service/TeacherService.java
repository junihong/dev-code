package com.tamsil.mocktest.service;

import com.tamsil.mocktest.entity.Lesson;
import com.tamsil.mocktest.entity.Teacher;
import com.tamsil.mocktest.repository.jpa.TeacherJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherJpaRepository teacherJpaRepository;
    private final LessonService lessonService;

    public Optional<Teacher> findByName(String name) {
        return teacherJpaRepository.findByName(name);
    }

    public Teacher create(Teacher teacher) {
        return teacherJpaRepository.save(teacher);
    }

    public List<Lesson> findLessonByTime(LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
        return lessonService.findLessonByTime(date, startTime, endTime);
    }
}
