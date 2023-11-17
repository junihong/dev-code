package com.tamsil.springbootsqlinit.repository;

import com.tamsil.springbootsqlinit.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
