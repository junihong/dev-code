package com.tamsil.mocktest.service;

import com.tamsil.mocktest.repository.jpa.LessonRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Mock
    private LessonRepository lessonRepository;
}
