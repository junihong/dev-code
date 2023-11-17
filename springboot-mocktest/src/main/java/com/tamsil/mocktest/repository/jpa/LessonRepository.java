package com.tamsil.mocktest.repository.jpa;

import com.tamsil.mocktest.entity.Lesson;
import com.tamsil.mocktest.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<List<Lesson>> findByDateAndStartTimeAndEndTime(LocalDate date, LocalDateTime startTime, LocalDateTime endTime);

    Optional<Lesson> findByDateAndStartTimeAndEndTimeAndTeacher(LocalDate date, LocalDateTime startTime, LocalDateTime endTime, Teacher teacher);
}
