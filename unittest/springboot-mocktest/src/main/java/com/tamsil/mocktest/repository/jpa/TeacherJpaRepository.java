package com.tamsil.mocktest.repository.jpa;

import com.tamsil.mocktest.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByName(String name);
}
