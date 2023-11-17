package com.tamsil.mocktest.repository.jpa;

import com.tamsil.mocktest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
}
