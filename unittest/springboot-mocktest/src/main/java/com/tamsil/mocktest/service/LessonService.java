package com.tamsil.mocktest.service;

import com.tamsil.mocktest.entity.Lesson;
import com.tamsil.mocktest.repository.jpa.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class LessonService {

    private LessonRepository lessonRepository;

    public LessonService() {
    }

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> findLessonByTime(LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
        return lessonRepository.findByDateAndStartTimeAndEndTime(date, startTime, endTime).orElseGet(()
                -> Collections.singletonList(this.createLesson(date, startTime, endTime)));
    }

    public Lesson createLesson(LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
        return Lesson.builder()
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }
}
